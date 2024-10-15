package me.hy.base.base.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import me.hy.base.base.viewmodel.BaseViewModel
import me.hy.base.ext.inflateBindingWithGeneric
import me.hy.base.ext.util.notNull

/**
 * 描述　: 包含ViewModel 和Databind ViewModelActivity基类，把ViewModel 和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {

    override fun layoutId() = 0

    lateinit var mDataBind: DB

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
        mDataBind = inflateBindingWithGeneric(layoutInflater)
        return mDataBind.root
    }
}