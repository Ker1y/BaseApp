package com.hy.baseapp.ui.home

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.common.extension.requestImagePermission
import com.hy.baseapp.common.utils.image.openGalleryImage
import com.hy.baseapp.common.utils.image.openSystemGalleryImageSingle
import com.hy.baseapp.databinding.ActivityMainBinding
import com.hy.baseapp.ui.viewmodel.HomeViewModel
import me.hy.jetpackmvvm.ext.view.click

class MainActivity : BaseActivity<HomeViewModel,ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        fullStatusBar()
    }
    override fun createObserver() {
        mDataBind.button.click {
            requestImagePermission {
                openGalleryImage {
                    LogUtils.d("$it")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("onCreate")
    }

}