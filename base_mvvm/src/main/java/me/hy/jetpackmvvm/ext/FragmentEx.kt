package me.hy.jetpackmvvm.ext

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/04/07
 *     desc  :
 *
 * </pre>
 */

inline fun <reified F : Fragment> FragmentActivity.newFragment(vararg args: Pair<String, String>): F {
    val bundle = Bundle()
    args.let {
        for (arg in args) {
            bundle.putString(arg.first, arg.second)
        }
    }
    val fragment = supportFragmentManager.instantiate(F::class.java.name) as F
    fragment.arguments = bundle
    return fragment
//    return Fragment.instantiate(this, F::class.java.name, bundle) as F
}

fun FragmentManager.instantiate(className: String): Fragment {
    return fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), className)
}