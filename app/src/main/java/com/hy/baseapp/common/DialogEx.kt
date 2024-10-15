package com.hy.baseapp.common

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.drake.tooltip.dialog.BubbleDialog


@SuppressLint("StaticFieldLeak")
var loadingDialog: LoadingDialog? = null

@SuppressLint("StaticFieldLeak")
var iosLoadingDialog: BubbleDialog? = null


fun FragmentActivity.showLoading(msg: String) {
    if (!this.isFinishing) {
        if (iosLoadingDialog == null) {
            iosLoadingDialog = BubbleDialog(this)
            iosLoadingDialog?.setCanceledOnTouchOutside(false)
        }
        iosLoadingDialog?.updateTitle(msg)
        iosLoadingDialog?.let {
            if (!it.isShowing) {
                try {
                    iosLoadingDialog?.show()
                } catch (e: Exception) {
                    iosLoadingDialog?.dismiss()
                    e.printStackTrace()
                }
            }
        }
    }
}

fun Fragment.showLoading(msg: String) {
    activity?.let {
        if (!it.isFinishing) {
            if (iosLoadingDialog == null) {
                iosLoadingDialog = BubbleDialog(it)
            }
            iosLoadingDialog?.updateTitle(msg)
            iosLoadingDialog?.let { dialog ->
                if (!dialog.isShowing) {
                    try {
                        iosLoadingDialog?.show()
                    } catch (e: Exception) {
                        iosLoadingDialog?.dismiss()
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}

/**
 * 关闭等待框
 */
fun FragmentActivity.dismissLoading() {
    iosLoadingDialog?.dismiss()
    iosLoadingDialog = null
}


fun Fragment.dismissLoading() {
    iosLoadingDialog?.dismiss()
    iosLoadingDialog = null
}
