@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package me.hy.base.ext.util

import android.content.ClipData
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.provider.Settings
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.widget.TextView
import me.hy.base.base.appContext


/**
 * 获取屏幕宽度
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * 获取屏幕高度
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

/**
 * 判断是否为空 并传入相关操作
 */
@kotlin.internal.InlineOnly
inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit = {}) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}

/**
 * 判断是否为空 并传入相关操作
 */
/*fun <T> Any?.notNull(f: () -> T, t: () -> T): T {
    return if (this != null) f() else t()
}*/


/**
 * px值转换成dp
 */
fun Int.px2dp(): Int {
    val scale = appContext.resources.displayMetrics.density
    return (this / scale + 0.5f).toInt()
}

/**
 * dp值转换为px
 */
fun Int.dp2px(): Int {
    val scale = appContext.resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

/**
 * dp->float px
 */
val Int.dpF: Float
    get() = this * Resources.getSystem().displayMetrics.density + 0.5f

val Int.pt: Int
    get() {
        val metrics = appContext.resources.displayMetrics
        return (this * metrics.xdpi / 72f + 0.5f).toInt()
    }



/**
 * 复制文本到粘贴板
 */
fun Context.copyToClipboard(text: String, label: String = "") {
    val clipData = ClipData.newPlainText(label, text)
    clipboardManager?.setPrimaryClip(clipData)
}

/**
 * 检查是否启用无障碍服务
 */
fun Context.checkAccessibilityServiceEnabled(serviceName: String): Boolean {
    val settingValue =
        Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
    var result = false
    val splitter = TextUtils.SimpleStringSplitter(':')
    while (splitter.hasNext()) {
        if (splitter.next().equals(serviceName, true)) {
            result = true
            break
        }
    }
    return result
}




fun String.toHtml(flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, flag)
    } else {
        Html.fromHtml(this)
    }

}

 fun setGradient(textView: TextView,startColor:String,endColor:String) {
    val endX = textView.paint.textSize * textView.text.length
    val linearGradient = LinearGradient(
        0f, 0f, endX, 0f,
        Color.parseColor(startColor),
        Color.parseColor(endColor),
        Shader.TileMode.CLAMP
    )
    textView.paint.shader = linearGradient
    textView.invalidate()
}