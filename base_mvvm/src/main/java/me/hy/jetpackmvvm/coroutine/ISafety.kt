package me.hy.jetpackmvvm.coroutine

import me.hy.jetpackmvvm.coroutine.CoroutineExt.launchSafety
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface ISafety {

    fun showProgress()

    fun hideProgress()

    fun showToast(content: String)

    fun <T> CoroutineScope.launchSafetyProgress(
        context: CoroutineContext = this.coroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ) = this.launchSafety(context, start) {
        showProgress()
        block.invoke(this)
    }.onCancel {
        hideProgress()
    }.onSuccess {
        hideProgress()
    }.onError {
        hideProgress()
    }

}