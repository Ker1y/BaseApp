package com.hy.baseapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import me.hy.base.network.BaseResponse

@Keep
data class ApiResponse<T>(
    @SerializedName("code", alternate = ["WiXY"])
    val code: Int,
    @SerializedName("msg", alternate = ["gma"])
    val msg: String,
    @SerializedName("data", alternate = ["XVnV"])
    val data: T
) : BaseResponse<T>() {

    // 这里是示例，网站返回的 错误码为 0 就代表请求成功，请你根据自己的业务需求来改变
    override fun isSuccess() = code == 200

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = msg

}