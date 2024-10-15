package com.hy.baseapp.common.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.webkit.WebView
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LanguageUtils
import com.blankj.utilcode.util.StringUtils
import com.drake.tooltip.dialog.BubbleDialog
import com.hy.baseapp.R
import com.hy.baseapp.base.event.App.Companion.appInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/25
 *     desc  :
 *
 * </pre>
 */

/**
 * 协程计时器
 */
fun tickerFlow(period: Long, initialDelay: Long = 0L) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}

/**
 * 协程倒计时
 */
fun countDownCoroutines(
    total: Int, onTick: (Int) -> Unit, onFinish: () -> Unit,
    scope: CoroutineScope = GlobalScope
): Job {
    return flow {
        for (i in total downTo 0) {
            emit(i)
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)
        .onCompletion { onFinish.invoke() }
        .onEach { onTick.invoke(it) }
        .flowOn(Dispatchers.Main)
        .launchIn(scope)
}

// 获取渠道工具函数
fun getChannelName(): String {
    var channelName: String? = null
    try {
        val packageManager = appInstance.packageManager
        if (packageManager != null) {
            //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是activity标签中，所以用ApplicationInfo
            val applicationInfo = packageManager.getApplicationInfo(
                appInstance.packageName,
                PackageManager.GET_META_DATA
            )
            if (applicationInfo.metaData != null) {
                channelName = applicationInfo.metaData["UMENG_CHANNEL"].toString()
            }
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (TextUtils.isEmpty(channelName)) {
        channelName = "Unknown"
    }
    return channelName ?: "null"
}


/**
 * 读取本地Json文件
 */
fun getJsonFromAssets(context: Context, fileName: String): String {
    val stringBuilder = StringBuilder()
    try {
        val assetManager = context.assets
        val bf = BufferedReader(
            InputStreamReader(
                assetManager.open(fileName)
            )
        )
        var line: String?
        while (bf.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stringBuilder.toString()
}


fun Int.asDrawableToUri(): Uri = Uri.parse(
    ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + appInstance.resources.getResourcePackageName(
        this
    ) + '/' + appInstance.resources.getResourceTypeName(this) + '/' + appInstance.resources.getResourceEntryName(
        this
    )
)

/**
 * 修复当某个WebView为单独进程时
 * 与自身进程WebView冲突报错
 * P以后WebView不允许共用目录
 */
fun fixWebViewDataDirectoryBug() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val processName: String = Application.getProcessName()
        val packageName = AppUtils.getAppPackageName()
        if (packageName != processName) {
            WebView.setDataDirectorySuffix(processName)
        }
    }
}

fun isLocalLanguageChinese(): Boolean {
    return LanguageUtils.getSystemLanguage().language == "ch" || LanguageUtils.getSystemLanguage().language == "ZN"
}

@SuppressLint("StaticFieldLeak")
private var mIosLoadingDialog: BubbleDialog? = null
fun showLoadingDialogEx(msg: String = StringUtils.getString(R.string.net_dialog_msg)) {
    val topActivity = ActivityUtils.getTopActivity()
    if (!topActivity.isFinishing) {
        if (mIosLoadingDialog == null) {
            mIosLoadingDialog = BubbleDialog(topActivity)
            mIosLoadingDialog?.setCanceledOnTouchOutside(false)
        }
        mIosLoadingDialog?.updateTitle(msg)
        mIosLoadingDialog?.let {
            if (!it.isShowing) {
                try {
                    mIosLoadingDialog?.show()
                } catch (e: Exception) {
                    mIosLoadingDialog?.dismiss()
                }
            }
        }
    }
}