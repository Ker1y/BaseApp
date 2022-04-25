package com.hy.baseapp

import android.os.Bundle
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.databinding.ActivityMainBinding
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel

class MainActivity : BaseActivity<BaseViewModel,ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        fullStatusBar()
    }


}