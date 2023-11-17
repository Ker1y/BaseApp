package com.hy.baseapp.common

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.drake.tooltip.dialog.BubbleDialog


@SuppressLint("StaticFieldLeak")
private var mLoadingDialog: LoadingDialog? = null

@SuppressLint("StaticFieldLeak")
private var mIosLoadingDialog: BubbleDialog? = null



fun FragmentActivity.showLoadingDialogEx(msg: String) {
    if (!this.isFinishing) {
        if (mIosLoadingDialog == null) {
            mIosLoadingDialog = BubbleDialog(this)
            mIosLoadingDialog?.setCanceledOnTouchOutside(false)
        }
        mIosLoadingDialog?.updateTitle(msg)
        mIosLoadingDialog?.let {
            if (!it.isShowing) {
                try {
                    mIosLoadingDialog?.show()
                } catch (e: Exception) {

                }
            }
        }
    }
}

fun Fragment.showLoadingDialogEx(msg: String) {
    activity?.let {
        if (!it.isFinishing) {
            if (mIosLoadingDialog == null) {
                mIosLoadingDialog = BubbleDialog(it)
            }
            mIosLoadingDialog?.updateTitle(msg)
            mIosLoadingDialog?.let { dialog ->
                if (!dialog.isShowing) {
                    try {
                        mIosLoadingDialog?.show()
                    } catch (e: Exception) {

                    }
                }
            }
        }
    }
}

/**
 * 关闭等待框
 */
fun FragmentActivity.dismissLoadingExt() {
    mIosLoadingDialog?.dismiss()
    mIosLoadingDialog = null
}


fun Fragment.dismissLoadingExt() {
    mIosLoadingDialog?.dismiss()
    mIosLoadingDialog = null
}
