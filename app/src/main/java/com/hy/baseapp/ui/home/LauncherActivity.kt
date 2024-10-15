package com.hy.baseapp.ui.home

import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.databinding.ActivityLauncherBinding
import me.hy.base.base.viewmodel.BaseViewModel
import me.hy.base.ext.startActivity

class LauncherActivity : BaseActivity<BaseViewModel, ActivityLauncherBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        immersionBar {
            statusBarDarkFont(true)
            fullScreen(true)
            transparentBar()
        }
//        initConfig()
        startUi()
    }

    override fun initClick() {

    }

    private fun initConfig() {
//        scopeNetLife {
//            while (appViewModel.globalConfiguration == null){
//                delay(2000)
//                appViewModel.globalConfiguration = Get<Configuration>(Api.getConfig).await()
//                startUi()
//            }
//        }
    }

    private fun startUi() {
        startActivity<MainActivity>()

//        if (appInstance.isLogin()) {
//            mViewModel.refreshUserInfo {
//                if (UserManager.checkUserInfoEffective()) {
//                    startActivity<MainActivity>()
//                    finish()
//                } else {
//                    startActivity<RegisterActivity>()
//                    finish()
//                }
//            }
//        } else {
//            startActivity<LoginActivity>()
//            finish()
//        }

    }
}