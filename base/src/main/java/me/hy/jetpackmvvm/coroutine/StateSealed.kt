package me.hy.jetpackmvvm.coroutine

sealed class StateSealed(val value: Int)
object ShowProgress : StateSealed(1)
object HideProgress : StateSealed(2)
class ShowToast(val content: String) : StateSealed(3)
