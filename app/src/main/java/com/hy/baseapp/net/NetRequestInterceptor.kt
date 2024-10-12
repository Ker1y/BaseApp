package com.hy.baseapp.net

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.NetworkUtils
import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest
import com.hy.baseapp.net.exception.NoNetException
import okhttp3.Headers

class NetRequestInterceptor : RequestInterceptor {
    override fun interceptor(request: BaseRequest) {
        if (!NetworkUtils.isAvailable()) {
            throw NoNetException()
        }

        val headers = Headers.Builder().apply {
            add("Content-Type", "application/json")
            add("Cookie", "ky_auth=;sdk=${DeviceUtils.getSDKVersionCode()}")
            add("app-vrn", AppUtils.getAppVersionName())
            add(
                "brand",
                "${android.os.Build.BRAND}_${android.os.Build.MODEL}_${android.os.Build.VERSION.RELEASE}"
            )
        }.build()

        request.setHeaders(headers)
    }
}