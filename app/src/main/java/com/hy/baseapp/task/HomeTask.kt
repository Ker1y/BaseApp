package com.hy.baseapp.task

import com.drake.net.Get
import com.drake.net.cache.CacheMode
import com.hy.baseapp.model.TestModel
import com.hy.baseapp.net.Api
import kotlinx.coroutines.coroutineScope

object HomeTask {
    suspend fun getDiscoverData(region: String, index: Int, count: Int,mode: CacheMode) = coroutineScope {
        val list = Get<MutableList<TestModel>>(Api.QUICK_LOGIN) {
            param("nVam", region)
            param("jVaYHi", index)
            param("jVaYMctY", count)
            setCacheMode(mode)
        }.await()
        list
    }
}