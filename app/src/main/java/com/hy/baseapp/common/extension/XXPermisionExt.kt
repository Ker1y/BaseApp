package com.hy.baseapp.common.extension

import android.content.Context
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

inline fun Context.requestImagePermission(crossinline callBack: () -> Unit) {
    XXPermissions.with(this).permission(Permission.READ_MEDIA_IMAGES)
        .request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                callBack.invoke()
            }

            override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                super.onDenied(permissions, never)
            }
        })
}