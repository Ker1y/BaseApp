package com.hy.baseapp.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import me.hy.jetpackmvvm.base.activity.BaseVmActivity
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel

abstract class BaseComposeActivity<VM : BaseViewModel>: BaseVmActivity<VM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

    override fun layoutId(): Int  = 0


    override fun initObserve() {}


}