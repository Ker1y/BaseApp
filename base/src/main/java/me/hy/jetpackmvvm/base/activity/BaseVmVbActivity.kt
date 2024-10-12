package me.hy.jetpackmvvm.base.activity

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hy.jetpackmvvm.ext.inflateBindingWithGeneric
import me.hy.jetpackmvvm.ext.util.notNull

/**
 * 描述　: 包含 ViewModel 和 ViewBinding ViewModelActivity基类，把ViewModel 和 ViewBinding 注入进来了
 * 需要使用 ViewBinding 的请继承它
 */
abstract class BaseVmVbActivity<VM : BaseViewModel, VB : ViewBinding> : BaseVmActivity<VM>() {

    override fun layoutId(): Int = 0

    lateinit var mViewBind: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        initDataBind().notNull({
            setContentView(it)
        }, {
            setContentView(layoutId())
        })
        super.onCreate(savedInstanceState)
    }

    /**
     * 创建DataBinding
     */
    override fun initDataBind(): View? {
        mViewBind = inflateBindingWithGeneric(layoutInflater)
        return mViewBind.root

    }
}