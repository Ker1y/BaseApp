package me.hy.jetpackmvvm.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.hy.jetpackmvvm.coroutine.applicationScope

/**
 * <pre>
 *
 *     author: Strawberry
 *     time  : 2024/2/4
 *     desc  :
 *
 * </pre>
 */

fun <T> MutableStateFlow<T>.observe(scope: FragmentActivity, block: suspend (T) -> Unit) {
    scope.lifecycleScope.launch {
        scope.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@observe.collectLatest {
                block.invoke(it)
            }
        }
    }
}

fun <T> MutableSharedFlow<T>.observe(
    scope: CoroutineScope,
    block: suspend (T) -> Unit
) {
    this.onEach(block).launchIn(scope)
}

fun <T> MutableSharedFlow<T>.observe(
    scope: FragmentActivity,
    block: suspend (T) -> Unit
) {
    this.observe(scope.lifecycleScope, block)
}

fun <T> MutableSharedFlow<T>.observe(
    scope: Fragment,
    block: suspend (T) -> Unit
) {
    this.observe(scope.lifecycleScope, block)
}

fun <T> MutableSharedFlow<T>.send(value: T) {
    this.send(applicationScope, value)
}

fun <T> MutableSharedFlow<T>.send(scope: CoroutineScope, value: T) {
    scope.launch {
        this@send.emit(value)
    }
}