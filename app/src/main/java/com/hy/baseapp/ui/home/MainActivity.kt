package com.hy.baseapp.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.base.event.eventViewModel
import com.hy.baseapp.common.extension.requestPermissions
import com.hy.baseapp.common.utils.image.openGalleryImage
import com.hy.baseapp.databinding.ActivityMainBinding
import com.hy.baseapp.viewmodel.HomeViewModel
import me.hy.jetpackmvvm.ext.observe
import me.hy.jetpackmvvm.ext.view.click

class MainActivity : BaseActivity<HomeViewModel,ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }
    override fun createObserver() {

        eventViewModel.testEvent.observe(this){
            //事件监听
        }

        mDataBind.button.click {
            requestPermissions {
                openGalleryImage {
                    LogUtils.d("${it?.get(0)?.availablePath}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("onCreate")
    }

}