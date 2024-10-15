package com.hy.baseapp.viewmodel

import androidx.lifecycle.scopeNetLife
import com.drake.net.cache.CacheMode
import com.hy.baseapp.model.TestModel
import com.hy.baseapp.net.stateCallback.ListDataUiState
import com.hy.baseapp.task.HomeTask
import me.hy.base.base.viewmodel.BaseViewModel

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/26
 *     desc  :
 *
 * </pre>
 */
class HomeViewModel:BaseViewModel() {

    val count = 10

    var selectRegion = ""
    inline fun getList(
        index: Int = 1,
        label: String = "",
        crossinline block: (ListDataUiState<TestModel>) -> Unit = {}
    ) {
        scopeNetLife {
            val list = HomeTask.getDiscoverData(selectRegion, index, count, CacheMode.WRITE)
            val state = ListDataUiState(
                isSuccess = true,
                isRefresh = index == 1,
                isLoadMore = index > 1,
                isEmpty = list.isEmpty(),
                isFirstEmpty = list.isEmpty() && index == 1,
                listData = list,
                hasMore = list.size >= count
            )
            block.invoke(state)
        }.preview(true) {
            val list = HomeTask.getDiscoverData(selectRegion, index, count, CacheMode.READ)
            val state = ListDataUiState(
                isSuccess = true,
                isRefresh = index == 1,
                isLoadMore = index > 1,
                isEmpty = list.isEmpty(),
                isFirstEmpty = list.isEmpty() && index == 1,
                listData = list,
                hasMore = list.size >= count
            )
            block.invoke(state)
        }.finally {

        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}