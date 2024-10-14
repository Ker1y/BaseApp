package com.hy.baseapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import me.hy.jetpackmvvm.base.fragment.BaseVmFragment
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel

abstract class BaseComposeFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            AppTheme {
                SetContent()
            }
        }
        return composeView
    }

    @Composable
    abstract fun SetContent()

    override fun initObserver() {}
    override fun initClick() {}
    override fun layoutId(): Int = 0
    override fun initView(savedInstanceState: Bundle?) {}
    override fun lazyLoadData() {}
}