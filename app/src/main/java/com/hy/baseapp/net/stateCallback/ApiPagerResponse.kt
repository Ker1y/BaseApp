package com.hy.baseapp.net.stateCallback

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable

/**
 *  分页数据的基类
 */
@Keep
@Parcelize
data class ApiPagerResponse<T>(
    var datas:@RawValue T,
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
) : Parcelable,Serializable {
    /**
     * 数据是否为空
     */
    fun isEmpty() = (datas as List<*>).isEmpty()

    /**
     * 是否为刷新
     */
    fun isRefresh() = offset == 0

    /**
     * 是否还有更多数据
     */
    fun hasMore() = !over
}