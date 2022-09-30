package com.ifun.meeting.common.utils.image

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hy.baseapp.common.utils.image.ImageLoaderUtils
import com.hy.baseapp.common.utils.image.buildOptions
import com.hy.baseapp.common.utils.image.getSandboxPath
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.engine.CropEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.utils.DateUtils
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropImageEngine
import java.io.File

/**
 * <pre>
 *
 * author: Hy
 * time  : 2022/7/21
 * desc  :
 *
</pre> *
 */
class ImageCropEngine : CropEngine {
    override fun onStartCrop(
        fragment: Fragment,
        currentLocalMedia: LocalMedia,
        dataSource: ArrayList<LocalMedia>,
        requestCode: Int
    ) {
        val currentCropPath = currentLocalMedia.availablePath
        val inputUri: Uri = if (PictureMimeType.isContent(currentCropPath) || PictureMimeType.isHasHttp(
                currentCropPath
            )
        ) {
            Uri.parse(currentCropPath)
        } else {
            Uri.fromFile(File(currentCropPath))
        }

        val fileName = DateUtils.getCreateFileName("CROP_") + ".jpg"
        val destinationUri = Uri.fromFile(File(getSandboxPath(), fileName))
        val options: UCrop.Options = buildOptions()
        val dataCropSource = ArrayList<String>()
        for (i in dataSource.indices) {
            val media = dataSource[i]
            dataCropSource.add(media.availablePath)
        }
        val uCrop = UCrop.of(inputUri, destinationUri, dataCropSource)
        //options.setMultipleCropAspectRatio(buildAspectRatios(dataSource.size()));
        uCrop.withOptions(options)
        uCrop.setImageEngine(object : UCropImageEngine {
            override fun loadImage(context: Context?, url: String?, imageView: ImageView?) {
                if (!ImageLoaderUtils.assertValidRequest(context)) {
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

            }
        })
        uCrop.start(fragment.requireActivity(), fragment, requestCode)
    }
}