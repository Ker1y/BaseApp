package me.hy.base.coroutine

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

val applicationScope: LifecycleCoroutineScope =
    ProcessLifecycleOwner.get().lifecycleScope

object CoroutineExt {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun <T> CoroutineScope.launchSafety(
        context: CoroutineContext = this.coroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): StatefulCoroutine<T> {
        val newContext = newCoroutineContext(context)
        val coroutine = StatefulCoroutine<T>(newContext)
        coroutine.start(start, coroutine) {
            delay(1)//防止block执行速度过快导致还没return
            block()
        }
        return coroutine
    }

    fun CoroutineScope.delayLaunch(
        timeMills: Long,
        block: suspend CoroutineScope.() -> Unit
    ): Job = launchSafety {
        if (timeMills > 0) {
            delay(timeMills)
        }
        block()
    }

    fun <Response> CoroutineScope.repeatLaunch(
        interval: Long,
        count: Int = Int.MAX_VALUE,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onStart: (() -> Unit)? = null,
        block: suspend (Int) -> Response,
        onResponse: ((result: Response) -> Unit)? = null,
        onFailure: (suspend (Throwable) -> Unit)? = null,
        onFinished: (() -> Unit)? = null
    ): Job = launch(context, start) {
        check(interval >= 0) { "interval time must be positive" }
        check(count > 0) { "repeat count must larger than 0" }
        onStart?.invoke()
        repeat(count) { index ->
            try {
                onResponse?.invoke(block(index)) ?: block(index)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                onFailure?.invoke(e)
            }
            delay(interval)
        }
        onFinished?.invoke()
    }


    suspend fun <T> doOnIO(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.IO, block)

    suspend fun <T> doOnMain(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Main, block)

    suspend fun <T> doOnDefault(block: suspend CoroutineScope.() -> T) =
        withContext(Dispatchers.Default, block)

}