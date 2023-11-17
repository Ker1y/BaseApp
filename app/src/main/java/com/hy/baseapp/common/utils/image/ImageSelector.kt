package com.hy.baseapp.common.utils.image

import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.hy.baseapp.base.event.App.Companion.appInstance
import java.io.File

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 9/13/2022
 *     desc  : Android原生SAF原生图片选择
 *
 * </pre>
 */

/**
 * 设置保存到本地的Uri
 */
fun getImageSaveUri():Uri{
    val file = File.createTempFile(
        "IMG_",
        ".jpg",
        appInstance.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    )
    val uri = FileProvider.getUriForFile(
        appInstance,
        "${appInstance.packageName}.fileprovider",
        file
    )
    file.deleteOnExit()
    return uri
}

/**
 * 查询本机图片
 */
fun queryImages() :MutableList<Uri>{
    val images = mutableListOf<Uri>()
    // 先拿到图片数据表的uri
    val tableUri:Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    // 需要获取数据表中的哪几列信息
    val projection:Array<String> = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.MIME_TYPE)
    // 查询条件，因为是查询全部图片，传null
    //String selection = MediaStore.Images.Media.DISPLAY_NAME +"= ?";
    // 条件参数 ，因为是查询全部图片，传null
    //String[] args = new String[] {“test”}
    // 排序：按id倒叙
    val order:String = MediaStore.Files.FileColumns._ID+" DESC"
    // 开始查询
    val cursor: Cursor? = appInstance.contentResolver.query(tableUri,projection,null,null, order)
    if (cursor != null) {
        // 获取id字段是第几列，该方法最好在循环之前做好
        val idIndex:Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
        // 获取data字段是第几列，该方法最好在循环之前做好
        val dataIndex:Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

        while (cursor.moveToNext()) {
            val id:Long = cursor.getLong(idIndex)
            // 获取到每张图片的uri
            val imageUri:Uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id)
            // 获取到每张图片的绝对路径
            val path:String = cursor.getString(dataIndex)
            // 做保存工作
            images.add(imageUri)
        }
        cursor.close()
    }
    return images
}