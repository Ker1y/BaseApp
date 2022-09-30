package com.hy.baseapp.common.utils.image

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * <pre>
 *
 * author: Hy
 * time  : 2022/7/21
 * desc  :
 *
</pre> *
 */
object ImageLoaderUtils {
    fun assertValidRequest(context: Context?): Boolean {
        if (context is Activity) {
            return !isDestroy(context)
        } else if (context is ContextWrapper) {
            if (context.baseContext is Activity) {
                val activity = context.baseContext as Activity
                return !isDestroy(activity)
            }
        }
        return true
    }

    private fun isDestroy(activity: Activity?): Boolean {
        return if (activity == null) {
            true
        } else activity.isFinishing || activity.isDestroyed
    }
}