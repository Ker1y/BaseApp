package com.hy.baseapp.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.ScreenUtils
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.hy.baseapp.R
import com.hy.baseapp.common.DESIGN_HEIGHT
import com.hy.baseapp.common.DESIGN_WIDTH
import com.noober.background.BackgroundLibrary
import me.hy.base.base.activity.BaseVmDbActivity
import me.hy.base.base.viewmodel.BaseViewModel

/**
 * 描述　: 你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    abstract override fun initView(savedInstanceState: Bundle?)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        //强制竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        BackgroundLibrary.inject(this)

        immersionBar {
            statusBarDarkFont(true)
        }

        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        //设置不息屏
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * @color 顶部状态栏颜色
     */
    fun immersionBar(@ColorRes color: Int = R.color.white) {
        ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(color)
            .statusBarDarkFont(true)
            .init()
    }

    fun setStatusBarView(view: View, darkFont: Boolean) {
        if (darkFont) {
            ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .titleBarMarginTop(view)
                .statusBarColor(R.color.white)
                .init()
        } else {
            ImmersionBar.with(this)
                .titleBarMarginTop(view)
                .statusBarColor(R.color.white)
                .init()
        }
    }

    /**
     * 沉浸状态栏
     */
    fun fullStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(true).transparentStatusBar().init()
    }


    /**
     * 点击空白处隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }

    /**
     * 创建liveData观察者
     */
    override fun initObserve() {}


    /**
     *  281，609根据设计图尺寸来 px
     *  xml预览dpi计算：
     *  val d = (281 * 281 + 609 * 609).toDouble()
     *  val dpi = sqrt(d)/72
     *  LogUtils.d("----------dpi-----$dpi")
     */
    override fun getResources(): Resources {
        return if (ScreenUtils.isPortrait()) {
            if (adaptWidth()) {
                AdaptScreenUtils.adaptWidth(super.getResources(), DESIGN_WIDTH.toInt())
            } else {
                AdaptScreenUtils.adaptHeight(super.getResources(), DESIGN_HEIGHT.toInt())
            }
        } else {
            if (adaptWidth()) {
                AdaptScreenUtils.adaptHeight(super.getResources(), DESIGN_WIDTH.toInt())
            } else {
                AdaptScreenUtils.adaptWidth(super.getResources(), DESIGN_HEIGHT.toInt())
            }
        }
    }

    open fun adaptWidth(): Boolean = true


    /* */
    /**
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     *//*
    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }*/
}