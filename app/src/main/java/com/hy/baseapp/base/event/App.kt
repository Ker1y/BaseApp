package com.hy.baseapp.base.event

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ProcessUtils
import com.drake.statelayout.StateConfig
import com.hy.baseapp.BuildConfig
import com.hy.baseapp.R
import com.hy.baseapp.base.event.App.Companion.appViewModelInstance
import com.hy.baseapp.base.event.App.Companion.eventViewModelInstance
import com.hy.baseapp.net.NetworkApi
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import me.hy.base.base.BaseApp

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/24
 *     desc  :
 *
 * </pre>
 */

//Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
val appViewModel: AppViewModel by lazy { appViewModelInstance }

//Application全局的ViewModel，用于发送全局通知操作
val eventViewModel: EventViewModel by lazy { eventViewModelInstance }

class App : BaseApp() {
    companion object {
        lateinit var appInstance: App
        lateinit var eventViewModelInstance: EventViewModel
        lateinit var appViewModelInstance: AppViewModel
    }


    override fun onCreate() {
        super.onCreate()
        appInstance = this
        eventViewModelInstance = getAppViewModelProvider()[EventViewModel::class.java]
        appViewModelInstance = getAppViewModelProvider()[AppViewModel::class.java]
        setConfig()
        if (ProcessUtils.isMainProcess()) {
            initOnMainProgress()
        }
    }

    /**
     * 初始化各种框架,SDK,配置信息
     */
    private fun setConfig(){
        MMKV.initialize(this)
//        AutoSizeConfig.getInstance().apply {
//            isExcludeFontScale = true
//            screenHeight = ScreenUtils.getScreenHeight()
//        }

        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG)
            .setLogHeadSwitch(true)
            .setBorderSwitch(true)
            .setSingleTagSwitch(true)
            .setGlobalTag("LOG")
            .setConsoleSwitch(true)

        NetworkApi.setNet()

    }

    private fun initOnMainProgress() {
        initSmartRefreshLayout()
//        BRV.modelId = BR.m
    }

    private fun initSmartRefreshLayout() {
        StateConfig.apply {
            emptyLayout = R.layout.state_empty
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context).setColorSchemeResources(
                R.color.main_color,
                R.color.main_color,
                R.color.main_color
            )
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, layout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableFooterTranslationContent(true)
            layout.setFooterHeight(50f)
            layout.setFooterTriggerRate(0.6f)

            ClassicsFooter(appInstance).apply {
//                ClassicsFooter.REFRESH_FOOTER_LOADING = getString(R.string.srl_footer_loading)
//                ClassicsFooter.REFRESH_FOOTER_FINISH = getString(R.string.srl_footer_finish)
//                ClassicsFooter.REFRESH_FOOTER_FAILED = getString(R.string.srl_footer_failed)
//                ClassicsFooter.REFRESH_FOOTER_NOTHING = getString(R.string.srl_footer_failed)
            }
        }
    }
}