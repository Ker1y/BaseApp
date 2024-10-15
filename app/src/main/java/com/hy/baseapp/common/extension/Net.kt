package com.hy.baseapp.common.extension

import android.app.Dialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ActivityUtils
import com.drake.net.scope.DialogCoroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


/**
 * 扩展一个在ViewModel中使用dialog的方法
 * 自动关联到栈顶Activity
 *
 * 作用域开始时自动显示加载对话框, 结束时自动关闭加载对话框
 * 可以设置全局对话框 [com.drake.net.NetConfig.dialogFactory]
 * 对话框被取消或者界面关闭作用域被取消
 *
 * @param dialog 仅该作用域使用的对话框
 * @param cancelable 对话框是否可取消
 * @param dispatcher 调度器, 默认运行在[Dispatchers.Main]即主线程下
 */
fun ViewModel.scopeDialog(
    activity: FragmentActivity = ActivityUtils.getTopActivity() as FragmentActivity,
    dialog: Dialog? = null,
    cancelable: Boolean? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: suspend CoroutineScope.() -> Unit
) = DialogCoroutineScope(activity, dialog, cancelable, dispatcher).launch(block)