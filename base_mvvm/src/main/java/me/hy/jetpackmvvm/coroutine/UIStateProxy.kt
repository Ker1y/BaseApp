package me.hy.jetpackmvvm.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.InternalCoroutinesApi
import me.hy.jetpackmvvm.coroutine.CoroutineExt.launchSafety
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel

interface UIStateImpl : ISafety {

//    @OptIn(InternalCoroutinesApi::class)
//    fun bindViewModelUIProxy(vararg viewModels: ViewModel) {
//        viewModels.forEach { viewModel ->
//            if (viewModel is BaseViewModel) {
//                viewModel.viewModelScope.launchSafety {
//                    viewModel.getLoadCallBackFlow().collect { state ->
//                        when (state) {
//                            HideProgress -> hideProgress()
//                            ShowProgress -> showProgress()
//                            is ShowToast -> showToast(state.content)
//                        }
//                    }
//                }
//            }
//        }
//    }

}