package com.hy.baseapp.net

import com.blankj.utilcode.util.StringUtils
import com.drake.net.NetConfig
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setDialogFactory
import com.drake.net.okhttp.setErrorHandler
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.net.okhttp.trustSSLCertificate
import com.drake.tooltip.dialog.BubbleDialog
import com.hy.baseapp.BuildConfig
import com.hy.baseapp.R
import com.hy.baseapp.base.event.App.Companion.appInstance
import com.hy.baseapp.common.utils.TokenInterceptor
import com.hy.baseapp.net.Api.BASE
import me.hy.jetpackmvvm.network.interceptor.CacheInterceptor
import me.hy.jetpackmvvm.network.interceptor.logging.LogInterceptor
import okhttp3.Cache
import java.io.File
import java.util.concurrent.TimeUnit


object NetworkApi {
    fun setNet() {
        NetConfig.initialize(BASE, appInstance) {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(LogInterceptor())
            addInterceptor(ErrorInterceptor())
            addInterceptor(TokenInterceptor())
            cookieJar(com.drake.net.cookie.PersistentCookieJar(appInstance))

            // Net支持Http缓存协议和强制缓存模式
            // 当超过maxSize最大值会根据最近最少使用算法清除缓存来限制缓存大小
            cache(Cache(File(appInstance.cacheDir, "net_cache"), 1024 * 1024 * 128))
            setRequestInterceptor(NetRequestInterceptor())
            setErrorHandler(NetworkingErrorHandler())
            trustSSLCertificate()
            setDebug(BuildConfig.DEBUG)
            setConverter(SerializationConverter())
            setDialogFactory {
                BubbleDialog(it, title = StringUtils.getString(R.string.net_dialog_msg))
            }
        }
    }

}



