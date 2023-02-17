package com.hy.baseapp.common.utils.image

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hy.baseapp.R
import com.hy.baseapp.base.event.appInstance
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.PictureSelectionConfig
import com.luck.picture.lib.engine.CropFileEngine
import com.luck.picture.lib.utils.StyleUtils
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropImageEngine
import com.yalantis.ucrop.model.AspectRatio
import java.io.File

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/7/21
 *     desc  : UCrop 图片裁剪引擎类
 *
 * </pre>
 */

class ImageFileCropEngine : CropFileEngine {
    override fun onStartCrop(
        fragment: Fragment,
        srcUri: Uri?,
        destinationUri: Uri?,
        dataSource: ArrayList<String>?,
        requestCode: Int
    ) {
        val options: UCrop.Options = buildOptions()
        val uCrop: UCrop = UCrop.of(srcUri!!, destinationUri!!, dataSource)
        uCrop.setImageEngine(object : UCropImageEngine {
            override fun loadImage(context: Context?, url: String?, imageView: ImageView?) {
                if (!assertValidRequest(context)) {
                    return
                }
                Glide.with(context!!).load(url).override(180, 180).into(imageView!!)
            }

            override fun loadImage(
                context: Context?,
                url: Uri?,
                maxWidth: Int,
                maxHeight: Int,
                call: UCropImageEngine.OnCallbackListener<Bitmap>?
            ) {
                Glide.with(context!!).asBitmap().load(url).override(maxWidth, maxHeight)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            call?.onCall(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            call?.onCall(null)
                        }
                    })
            }
        })
        uCrop.withOptions(options)
        uCrop.start(fragment.requireActivity(), fragment, requestCode)
    }

    /**
     * 配制UCrop，可根据需求自我扩展
     *
     * @return
     */
    private fun buildOptions(): UCrop.Options {
        val options = UCrop.Options()
        options.setHideBottomControls(true)
        options.setFreeStyleCropEnabled(true)
        options.setShowCropFrame(true)
        options.setShowCropGrid(false)
        options.setCircleDimmedLayer(true)
        options.withAspectRatio(0f, 0f)
        options.setCropOutputPathDir(getSandboxPath())
        options.isCropDragSmoothToCenter(false)
        options.setSkipCropMimeType(*getNotSupportCrop())
        options.isForbidCropGifWebp(false)
        options.isForbidSkipMultipleCrop(false)
        options.setMaxScaleMultiplier(100f)
        if (PictureSelectionConfig.selectorStyle != null && PictureSelectionConfig.selectorStyle.selectMainStyle.statusBarColor != 0) {
            val mainStyle = PictureSelectionConfig.selectorStyle.selectMainStyle
            val isDarkStatusBarBlack = mainStyle.isDarkStatusBarBlack
            val statusBarColor = mainStyle.statusBarColor
            options.isDarkStatusBarBlack(isDarkStatusBarBlack)
            if (StyleUtils.checkStyleValidity(statusBarColor)) {
                options.setStatusBarColor(statusBarColor)
                options.setToolbarColor(statusBarColor)
            } else {
                options.setStatusBarColor(
                    ContextCompat.getColor(
                        appInstance,
                        R.color.gray_66
                    )
                )
                options.setToolbarColor(
                    ContextCompat.getColor(
                        appInstance,
                        R.color.gray_66
                    )
                )
            }
            val titleBarStyle = PictureSelectionConfig.selectorStyle.titleBarStyle
            if (StyleUtils.checkStyleValidity(titleBarStyle.titleTextColor)) {
                options.setToolbarWidgetColor(titleBarStyle.titleTextColor)
            } else {
                options.setToolbarWidgetColor(
                    ContextCompat.getColor(
                        appInstance,
                        R.color.white
                    )
                )
            }
        } else {
            options.setStatusBarColor(
                ContextCompat.getColor(
                    appInstance,
                    R.color.gray_66
                )
            )
            options.setToolbarColor(ContextCompat.getColor(appInstance, R.color.gray_66))
            options.setToolbarWidgetColor(
                ContextCompat.getColor(
                    appInstance,
                    R.color.white
                )
            )
        }
        return options
    }

    /**
     * 创建自定义输出目录
     *
     * @return
     */
    private fun getSandboxPath(): String {
        val externalFilesDir: File? = appInstance.getExternalFilesDir("")
        val customFile = File(externalFilesDir?.absolutePath, "Sandbox")
        if (!customFile.exists()) {
            customFile.mkdirs()
        }
        return customFile.absolutePath + File.separator
    }

    /**
     * 设置不支持裁剪的类型
     */
    private fun getNotSupportCrop(): Array<String> {
        return arrayOf(PictureMimeType.ofGIF(), PictureMimeType.ofWEBP())
    }


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

    /**
     * 多图裁剪时每张对应的裁剪比例
     *
     * @param dataSourceCount
     * @return
     */
    private fun buildAspectRatios(dataSourceCount: Int): Array<AspectRatio?> {
        val aspectRatios: Array<AspectRatio?> = arrayOfNulls<AspectRatio>(dataSourceCount)
        for (i in 0 until dataSourceCount) {
            when (i) {
                0 -> {
                    aspectRatios[i] = AspectRatio("16:9", 16f, 9f)
                }
                1 -> {
                    aspectRatios[i] = AspectRatio("3:2", 3f, 2f)
                }
                else -> {
                    aspectRatios[i] = AspectRatio("原始比例", 0f, 0f)
                }
            }
        }
        return aspectRatios
    }
}