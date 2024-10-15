package com.hy.baseapp.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.base.event.eventViewModel
import com.hy.baseapp.databinding.ActivityMainBinding
import com.hy.baseapp.viewmodel.HomeViewModel
import me.hy.base.ext.observe
import me.hy.base.ext.startActivity
import me.hy.base.ext.view.click

class MainActivity : BaseActivity<HomeViewModel, ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initObserve() {

        eventViewModel.testEvent.observe(this) {
            //事件监听
        }
    }

    override fun initClick() {
        mDataBind.button.click {
            startActivity<TestActivity>()
//            requestPermissions {
//                openGalleryImage {
//                    LogUtils.d("${it?.get(0)?.availablePath}")
//                }
//            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("onCreate")
    }

}