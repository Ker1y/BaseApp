package com.hy.baseapp.net.stateCallback

import androidx.annotation.Keep

/**
 * 描述　: 列表数据状态类
 */
@Keep
data class ListDataUiState<T>(
    //是否请求成功
    val isSuccess: Boolean,
    //错误消息 isSuccess为false才会有
    val errMessage: String = "",
    //是否为刷新
    val isRefresh: Boolean = false,
    //是否是加载更多
    val isLoadMore: Boolean = false,
    //是否为空
    val isEmpty: Boolean = false,
    //没有更多了
    val hasMore: Boolean = false,
    //是第一页且没有数据
    val isFirstEmpty:Boolean = false,
    //列表数据
    val listData: MutableList<T> = mutableListOf(),
    //定义一个Key，可能特殊情况需要做区分
    val custom:String = ""
)