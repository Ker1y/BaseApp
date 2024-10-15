package com.hy.baseapp.net

import com.blankj.utilcode.util.LogUtils
import com.hy.baseapp.model.ApiResponse
import me.hy.base.ext.fromJson
import me.hy.base.network.AppException
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 555) {
            val responseBody = response.body ?: return response
            val bytes = toByteArray(responseBody.byteStream())
            val contentType = responseBody.contentType()
            val body = String(bytes, getCharset(contentType) ?: UTF8)
            val result = try {
                body.fromJson()
            } catch (e: Exception) {
                ApiResponse(-1, "-201", "json error")
            }
            handleResult(result)
        }
        return response
    }

    private fun handleResult(result: ApiResponse<*>?) {
        result ?: return
        LogUtils.e("request failed:requestCode = ${result.code},message= ${result.msg}")
        throw AppException(result.code, result.msg)
    }

    private fun toByteArray(input: InputStream): ByteArray {
        val output = ByteArrayOutputStream()
        write(input, output)
        output.close()
        return output.toByteArray()
    }

    private fun write(
        inputStream: InputStream,
        outputStream: OutputStream
    ) {
        var len: Int
        val buffer = ByteArray(4096)
        while (inputStream.read(buffer).also { len = it } != -1) outputStream.write(
            buffer,
            0,
            len
        )
    }

    private fun getCharset(contentType: MediaType?): Charset? {
        return contentType?.charset(UTF8) ?: UTF8
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }
}

const val ACCOUNT_NOT_EXIST = "20005"
const val ACCOUNT_FORBIDDEN = "20009"
const val IN_BLOCK_LIST = "20015"
const val INSUFFICIENT_BALANCE = "10008"