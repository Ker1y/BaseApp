package com.hy.baseapp.common.utils.image

import android.app.Activity
import androidx.core.content.ContextCompat
import com.hy.baseapp.R
import com.hy.baseapp.base.event.appInstance
import com.hy.baseapp.common.utils.isLocalLanguageChinese
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.*
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.engine.CropFileEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnExternalPreviewEventListener
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.language.LanguageConfig
import com.luck.picture.lib.utils.StyleUtils
import com.yalantis.ucrop.UCrop
import java.io.File


/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/7/21
 *     desc  : PictureSelector图片选择扩展类，根据业务需求在这里添加选择相册需要的函数
 *
 *     原始库wiki：https://github.com/LuckSiege/PictureSelector/blob/version_component/README_CN.md
 *
 * </pre>
 */



/**
 * 打开相册，允许裁剪，允许拍照，单选
 */
fun Activity.openGallerySingleImage(callBack: (ArrayList<LocalMedia>?) -> Unit) {
    openGallery(
        SelectMimeType.ofImage(),
        selectionMode = SelectModeConfig.SINGLE,
        disPlayCamera = true,
//        cropEngine = ImageFileCropEngine(),
        compressEngine = ImageFileCompressEngine(),
        callBack = callBack,
        maxSelectNum = 1,
    )
}



/**
 * 打开相册，允许裁剪，允许拍照
 * @param num 最大选择数量
 */
fun Activity.openGalleryImage(num : Int = 5, callBack: (ArrayList<LocalMedia>?) -> Unit) {
    openGallery(
        SelectMimeType.ofImage(),
        selectionMode = SelectModeConfig.MULTIPLE,
        disPlayCamera = true,
//        cropEngine = ImageFileCropEngine(),
        compressEngine = ImageFileCompressEngine(),
        callBack = callBack,
        maxSelectNum = num,
    )
}

/**
 * 打开相册，允许裁剪，不允许拍照
 * @param num 最大选择数量
 */
fun Activity.openGalleryImageNoTake(num : Int = 5, callBack: (ArrayList<LocalMedia>?) -> Unit) {
    openGallery(
        SelectMimeType.ofImage(),
        selectionMode = SelectModeConfig.MULTIPLE,
        disPlayCamera = false,
//        cropEngine = ImageFileCropEngine(),
        compressEngine = ImageFileCompressEngine(),
        callBack = callBack,
        maxSelectNum = num,
    )
}

/**
 * 打开相册，选择视频
 * @param num 最大选择数量
 */
fun Activity.openGalleryVideoNoTake(num : Int = 5, callBack: (ArrayList<LocalMedia>?) -> Unit) {
    openGallery(
        SelectMimeType.ofVideo(),
        selectionMode = SelectModeConfig.MULTIPLE,
        disPlayCamera = false,
//        cropEngine = ImageFileCropEngine(),
        compressEngine = ImageFileCompressEngine(),
        callBack = callBack,
        maxSelectNum = num,
    )
}


/**
 * 获取相册图片数据
 * @param type 类型
 * @see SelectMimeType
 */
fun Activity.getMediaDataSource(type:Int,callBack:(MutableList<LocalMedia>)->Unit){
    PictureSelector.create(this).dataSource(type).obtainMediaData {
        callBack.invoke(it)
    }
}



/**
 * 打开系统相册
 * 仅限图片，单选，
 */
fun Activity.openSystemGalleryImageSingle(callBack: (ArrayList<LocalMedia>?) -> Unit){
    openSystemGallery(SelectMimeType.ofImage(),SelectModeConfig.SINGLE,callBack)
}
/**
 * 打开系统相機
 *
 */
fun Activity.openCamera(callBack: (ArrayList<LocalMedia>?) -> Unit){
    PictureSelector.create(this)
        .openCamera(SelectMimeType.ofImage())
        .forResult(object :OnResultCallbackListener<LocalMedia>{
        override fun onResult(result: java.util.ArrayList<LocalMedia>?) {
            callBack.invoke(result)
        }

        override fun onCancel() {

        }

    })
}
/**
 * 图片预览
 *
 */
fun Activity.openPreview(position : Int,imgList: java.util.ArrayList<LocalMedia>?){
    PictureSelector.create(this)
        .openPreview()
        .setImageEngine(GlideEngine.createGlideEngine())
        .setExternalPreviewEventListener(object : OnExternalPreviewEventListener {
            override fun onPreviewDelete(position: Int) {}
            override fun onLongPressDownload(media: LocalMedia): Boolean {
                return false
            }
        }).startActivityPreview(position, true, imgList)
}

/**
 * 打开系统相册
 * @param mimeType 相册类型，图片/视频
 *
 */
fun Activity.openSystemGallery(mimeType: Int,selectionMode:Int, callBack: (ArrayList<LocalMedia>?) -> Unit) {
    PictureSelector.create(this).openSystemGallery(mimeType)
        .setSkipCropMimeType(*getNotSupportCrop())
        .setCompressEngine(ImageFileCompressEngine())
//        .setCropEngine(ImageFileCropEngine())
        .setSelectionMode(selectionMode)
        .forSystemResult(object : OnResultCallbackListener<LocalMedia> {
            override fun onResult(result: ArrayList<LocalMedia>?) {
                callBack.invoke(result)
            }

            override fun onCancel() {}
        })
}


/**
 *
 *  打开相册的基础方法，外部禁止调用
 *
 * @param mimeType 文件类型
 * @param language 相册语言
 * @param cropEngine 裁剪引擎
 * @param compressEngine 压缩引擎
 * @param selectedData 已选照片
 * @param imageCount 相册列表每行显示个数
 * @param disPlayCamera 是否显示相机入口
 * @param isPageStrategy 是否开启分页模式
 * @param selectionMode  单选或是多选
 * @param maxSelectNum 图片最大选择数量
 * @param minSelectNum  图片最小选择数量
 * @param maxVideoSelectNum 视频最大选择数量
 * @param mineVideoSelectNum 视频最小选择数量
 * @param videoRecordMaxSecond 视频录制最大时长
 * @param videoRecordMinSecond 视频录制最小时长
 * @param videoQuality 系统相机录制视频质量
 * @param isQuickCapture 使用系统摄像机录制后，是否支持使用系统播放器立即播放视频
 * @param isPreviewAudio 是否支持音频预览
 * @param isPreviewImage 是否支持预览图片
 * @param isPreviewVideo 是否支持预览视频
 * @param withSelectVideoImage 是否支持视频图片同选
 * @param isCameraRotateImage  拍照是否纠正旋转图片
 * @param isGif  是否显示gif文件
 * @param isWebp 是否显示webp文件
 * @param isBmp 是否显示bmp文件
 * @param isMaxSelectEnabledMask 达到最大选择数是否开启禁选蒙层
 * @param isDirectReturnSingle 单选时是否立即返回
 * @param isAutoVideoPlay  预览视频是否自动播放
 * @param isLoopAutoVideoPlay 预览视频是否循环播放
 * @param isFilterSizeDuration 过滤视频小于1秒和文件小于1kb
 */

private fun Activity.openGallery(
    mimeType: Int,
    language: Int = if (isLocalLanguageChinese()) LanguageConfig.CHINESE else LanguageConfig.ENGLISH,
    cropEngine: CropFileEngine? = null,
    compressEngine: CompressFileEngine? = null,
    selectedData: MutableList<LocalMedia>? = null,
    imageCount: Int = 4,
    disPlayCamera: Boolean = false,
    isPageStrategy: Boolean = false,
    selectionMode: Int = SelectModeConfig.SINGLE,
    maxSelectNum: Int = 8,
    minSelectNum: Int = 1,
    maxVideoSelectNum: Int = 4,
    mineVideoSelectNum: Int = 1,
    videoRecordMaxSecond: Int = 60,
    videoRecordMinSecond: Int = 60,
    videoQuality: Int = VideoQuality.VIDEO_QUALITY_HIGH,
    isQuickCapture: Boolean = true,
    isPreviewAudio: Boolean = true,
    isPreviewImage: Boolean = true,
    isPreviewVideo: Boolean = true,
    withSelectVideoImage: Boolean = true,
    isCameraRotateImage: Boolean = true,
    isGif: Boolean = true,
    isWebp: Boolean = true,
    isBmp: Boolean = true,
    isMaxSelectEnabledMask: Boolean = true,
    isDirectReturnSingle: Boolean = false,
    isAutoVideoPlay: Boolean = true,
    isLoopAutoVideoPlay: Boolean = false,
    isFilterSizeDuration: Boolean = true,
    callBack: (ArrayList<LocalMedia>?) -> Unit = {}
) {
        PictureSelector.create(this).openGallery(mimeType).apply {
        setImageEngine(GlideEngine.createGlideEngine())
        setLanguage(language)
        if (cropEngine != null) setCropEngine(cropEngine)
        if (compressEngine != null) setCompressEngine(compressEngine)
        if (selectedData != null) setSelectedData(selectedData)
        setImageSpanCount(imageCount)
        isDisplayCamera(disPlayCamera)
        isPageStrategy(isPageStrategy)
        setSelectionMode(selectionMode)
        setMaxSelectNum(maxSelectNum)
        setMinSelectNum(minSelectNum)
        setMinVideoSelectNum(mineVideoSelectNum)
        setMaxVideoSelectNum(maxVideoSelectNum)
        setRecordVideoMaxSecond(videoRecordMaxSecond)
        setRecordVideoMinSecond(videoRecordMinSecond)
        setVideoQuality(videoQuality)
        isQuickCapture(isQuickCapture)
        isPreviewAudio(isPreviewAudio)
        isPreviewImage(isPreviewImage)
        isPreviewVideo(isPreviewVideo)
        isWithSelectVideoImage(withSelectVideoImage)
        isCameraRotateImage(isCameraRotateImage)
        isGif(isGif)
        isWebp(isWebp)
        isBmp(isBmp)
        isMaxSelectEnabledMask(isMaxSelectEnabledMask)
        isDirectReturnSingle(isDirectReturnSingle)
        isLoopAutoVideoPlay(isLoopAutoVideoPlay)
        isAutoVideoPlay(isAutoVideoPlay)
        isFilterSizeDuration(isFilterSizeDuration)
    }.forResult(object : OnResultCallbackListener<LocalMedia> {
        override fun onResult(result: ArrayList<LocalMedia>?) {
            callBack.invoke(result)
        }

        override fun onCancel() {}
    })
}




/**
 * 配制UCrop，可根据需求自我扩展
 *
 * @return
 */
fun buildOptions(): UCrop.Options {
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
fun getSandboxPath(): String {
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
fun getNotSupportCrop(): Array<String> {
    return arrayOf(PictureMimeType.ofGIF(), PictureMimeType.ofWEBP())
}

/**
 * 获取相册选择有效路径
 * 首选压缩后的路径
 * @return maybe ""
 */
fun LocalMedia?.getLeastOnePath(): String {
    if (this == null) return ""
    return this.compressPath ?: this.originalPath ?: this.realPath ?: this.path ?: ""
}



