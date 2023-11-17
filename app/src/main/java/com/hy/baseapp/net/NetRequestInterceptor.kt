package com.hy.baseapp.net

import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest
import com.hy.baseapp.base.event.App
import com.hy.bella.net.exception.NoNetException
import me.hy.jetpackmvvm.network.NetworkUtil

class NetRequestInterceptor:RequestInterceptor {
    override fun interceptor(request: BaseRequest) {
        if (!NetworkUtil.isNetworkAvailable(App.appInstance)){
            throw NoNetException()
        }
    }
}