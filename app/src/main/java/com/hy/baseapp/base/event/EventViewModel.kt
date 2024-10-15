package com.hy.baseapp.base.event

import kotlinx.coroutines.flow.MutableSharedFlow
import me.hy.base.base.viewmodel.BaseViewModel

/**
 * 描述　:APP全局的ViewModel，可以在这里发送全局通知替代EventBus，LiveDataBus等
 */
class EventViewModel : BaseViewModel() {

    val testEvent = MutableSharedFlow<Boolean>()

}