package me.hy.jetpackmvvm.ext.util

import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/26
 *     desc  :
 *
 * </pre>
 */

fun String.shortToast(gravity: Int = Gravity.CENTER){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#FF000000"))
        .setTextColor(Color.parseColor("#FFFFFF"))
        .setGravity(gravity,0,100)
        .setDurationIsLong(false)
        .show(this)
}

fun String.longToast(){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#FF000000"))
        .setTextColor(Color.parseColor("#FFFFFF"))
        .setGravity(Gravity.CENTER,0,100)
        .setDurationIsLong(true)
        .show(this)
}