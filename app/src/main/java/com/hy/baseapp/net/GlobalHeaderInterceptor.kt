package com.hy.baseapp.net

import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest

class GlobalHeaderInterceptor:RequestInterceptor {
    override fun interceptor(request: BaseRequest) {
        request.setHeader("client", "Android")
//        request.setHeader("token", UserConfig.token)
    }
}