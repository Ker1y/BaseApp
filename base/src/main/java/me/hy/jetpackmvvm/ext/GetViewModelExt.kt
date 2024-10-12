package me.hy.jetpackmvvm.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import me.hy.jetpackmvvm.base.BaseApp
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType


/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
fun <VM : ViewModel> getViewModelByReflect(owner: ViewModelStoreOwner) =
    try {
        val type = owner.javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0]
        ViewModelProvider(owner)[clazz as Class<VM>]
    } catch (e: Exception) {
        throw RuntimeException(e)
    }

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
inline fun <reified VM : ViewModel> getVmClazz(any: Any): Class<VM> {
    // 使用反射获取泛型的实际类型并返回 Java Class 类型
    return (any.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0] as Class<VM>
}

/**
 * 在Activity中得到Application上下文的ViewModel
 */
inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (this.application as? BaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}

/**
 * 在Fragment中得到Application上下文的ViewModel
 * 提示，在fragment中调用该方法时，请在该Fragment onCreate以后调用或者请用by lazy方式懒加载初始化调用，不然会提示requireActivity没有导致错误
 */
inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
    (this.requireActivity().application as? BaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}






