package me.hy.base.coroutine

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