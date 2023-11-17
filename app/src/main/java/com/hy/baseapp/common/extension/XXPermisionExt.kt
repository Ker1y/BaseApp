package com.hy.baseapp.common.extension

import android.content.Context
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hy.baseapp.base.event.App.Companion.appInstance
import me.hy.jetpackmvvm.ext.util.shortToast

inline fun Context.requestPermissions(crossinline callBack: () -> Unit) {
    XXPermissions.with(this).permission(Permission.READ_MEDIA_IMAGES)
        .request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                if (allGranted) {
                    callBack.invoke()
                }
            }

            override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                if (doNotAskAgain) {
//                    shortToast("")
                    XXPermissions.startPermissionActivity(appInstance, permissions);
                } else {
//                    shortToast("")
                }
                super.onDenied(permissions, doNotAskAgain)
            }
        })
}