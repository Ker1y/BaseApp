package com.hy.baseapp.common.utils

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
//        if (ConfigurationManager.isTokenExpired()) {
//            //同步请求方式，获取最新的Token
//            val newToken = getNewToken()
//            return chain.proceed(request)
//        }
        return chain.proceed(request)
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
//    @Throws(IOException::class)
//    private fun getNewToken(): String {
//        val formBody =
//            FormBody.Builder().add("lYZlYmbNieYh", ConfigurationManager.getRefreshToken()).build()
//        var request =
//            Request.Builder().url(Api.BASE + Api.refreshToken)
//                .post(formBody).build()
//        request = NetworkApi.INSTANCE.toRequestAddHeader(request).build()
//        try {
//            val newResponse = OkHttpClient.Builder().build().newCall(request).execute()
//            if (newResponse.code == 200) {
//                val result = newResponse.body?.string() ?: ""
//                LogUtils.e("getNewToken =$result")
//                val token = result.fromJson<AuthTokenModel>()
//                val time = token.refreshTime
//                if (time != 0L) {
//                    ConfigurationManager.saveConfig {
//                        this.accessToken = token.accessToken
//                        this.refreshToken = token.refreshToken
//                        this.refreshTime = time
//                    }
//                } else {
//                    appInstance.exitLogin()
//                }
//            } else {
//                appInstance.exitLogin()
//            }
//        } catch (e: Exception) {
//            if (e is UnknownHostException) {
////                ToastUtils.showShort(appContext.getString(R.string.state_network_not_connected))
//            }
//        } finally {
//
//        }
//        return ""
//    }
}