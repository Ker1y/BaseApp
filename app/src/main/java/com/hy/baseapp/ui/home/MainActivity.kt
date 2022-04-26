package com.hy.baseapp.ui.home

import android.os.Bundle
import com.hi.dhl.ktkit.ui.intent
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.databinding.ActivityMainBinding
import com.hy.baseapp.ui.viewmodel.HomeViewModel
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel

class MainActivity : BaseActivity<HomeViewModel,ActivityMainBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        fullStatusBar()
    }

    override fun createObserver() {
        super.createObserver()
    }
}