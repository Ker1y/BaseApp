package com.hy.baseapp.base

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar
import me.hy.base.base.fragment.BaseVmDbFragment
import me.hy.base.base.viewmodel.BaseViewModel

/**
 * 描述　: 你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmFragment例如
 * abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

    }

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun initObserver() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {}


    override fun onPause() {
        super.onPause()
//        hideSoftKeyboard(activity)
    }

    /**
     * @color 顶部状态栏颜色
     */
    fun setStatusBarColor(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this)
            .statusBarDarkFont(true, 0.2f)
            .fitsSystemWindows(true)
            .statusBarColor(statusBarColor)
            .statusBarDarkFont(true)
            .init()
    }


    /**
     * 沉浸状态栏
     */
    fun immersionFullStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(true).transparentStatusBar().init()
    }


    override fun onDestroy() {
//        dismissLoading()
        super.onDestroy()
    }

    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    override fun lazyLoadTime(): Long {
        return 300
    }
}