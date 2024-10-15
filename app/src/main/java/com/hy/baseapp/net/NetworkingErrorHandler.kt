package com.hy.baseapp.net

import com.drake.net.NetConfig
import com.drake.net.exception.ConvertException
import com.drake.net.exception.DownloadFileException
import com.drake.net.exception.HttpFailureException
import com.drake.net.exception.NetConnectException
import com.drake.net.exception.NetException
import com.drake.net.exception.NetSocketTimeoutException
import com.drake.net.exception.NoCacheException
import com.drake.net.exception.RequestParamsException
import com.drake.net.exception.ResponseException
import com.drake.net.exception.ServerResponseException
import com.drake.net.exception.URLParseException
import com.drake.net.interfaces.NetErrorHandler
import com.drake.tooltip.toast
import com.hy.baseapp.R
import com.hy.baseapp.common.extension.getResStr
import com.hy.baseapp.net.exception.NoNetException
import me.hy.base.base.appContext
import me.hy.base.network.AppException
import java.net.UnknownHostException

class NetworkingErrorHandler :NetErrorHandler {
    override fun onError(e: Throwable) {
        var message = when (e) {
            // .... 其他错误
            is UnknownHostException -> NetConfig.app.getString(R.string.net_host_error)
            is URLParseException -> NetConfig.app.getString(R.string.net_url_error)
            is NetConnectException -> NetConfig.app.getString(R.string.net_connect_error)
            is NetSocketTimeoutException -> NetConfig.app.getString(R.string.net_connect_timeout_error)
            is DownloadFileException -> NetConfig.app.getString(R.string.net_download_error)
            is ConvertException -> NetConfig.app.getString(R.string.net_parse_error)
            is RequestParamsException -> NetConfig.app.getString(R.string.net_request_error)
            is ServerResponseException -> NetConfig.app.getString(R.string.net_server_error)
            is NullPointerException -> NetConfig.app.getString(R.string.net_null_error)
            is NoCacheException -> NetConfig.app.getString(R.string.net_no_cache_error)
            is ResponseException -> e.message
            is HttpFailureException -> NetConfig.app.getString(R.string.request_failure)
            //APP 自定义异常
            is NoNetException -> getResStr(R.string.network_not_available)
            is NetException -> NetConfig.app.getString(R.string.net_error)
            is AppException -> e.message
            else -> NetConfig.app.getString(R.string.net_other_error)
        }
        /**
         * 10001 ：授权过期
         * 10002 ：签名错误
         * 10003 ：APP版本不支持，检查app版本（强更新需弹出强更提示）
         * 10004 ：参数错误
         * 10005 ：缺少参数
         * 10006 ：服务内部调用错误
         * 10007 ：上传内容超过限制
         * 10008 ：余额不足
         */

        if (e is AppException) {
            message = when(e.errCode){
                1000 -> appContext.getString(R.string.request_failed)
                1001 -> appContext.getString(R.string.parse_error)
                1002 -> appContext.getString(R.string.network_error)
                1004 -> appContext.getString(R.string.ssl_error)
                1006 -> appContext.getString(R.string.network_timeout)
                else -> ""
            }
        }

        if (message?.isNotEmpty() == true) {
            toast(message)
        }
        e.printStackTrace()
    }
}