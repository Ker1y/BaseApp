package me.hy.jetpackmvvm.ext.util

import android.graphics.Color
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import java.util.regex.Pattern

/**
 * 是否为手机号  0开头 12开头的不支持
 */
fun String?.isPhone(): Boolean {
    return this?.let {
        Pattern.matches(it, "0?(13|14|15|16|17|18|19)[0-9]{9}")
    }?:let {
       false
    }
}

/**
 * 是否为座机号
 */
fun String?.isTel(): Boolean {
    return this?.let {
        val matcher1 = Pattern.matches("^0(10|2[0-5|789]|[3-9]\\d{2})\\d{7,8}\$", this)
        val matcher2 = Pattern.matches("^0(10|2[0-5|789]|[3-9]\\d{2})-\\d{7,8}$", this)
        val matcher3 = Pattern.matches("^400\\d{7,8}$", this)
        val matcher4 = Pattern.matches("^400-\\d{7,8}$", this)
        val matcher5 = Pattern.matches("^800\\d{7,8}$", this)
        val matcher6 = Pattern.matches("^800-\\d{7,8}$", this)
        return matcher1 || matcher2 || matcher3 || matcher4 || matcher5 || matcher6
    }?:let {
        false
    }
}

/**
 * 是否为邮箱号
 */
fun String?.isEmail(): Boolean {
    return this?.let {
        Pattern.matches(this, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$")
    }?:let {
        false
    }
}

/**
 * 将对象转为JSON字符串
 */
fun Any?.toJson():String{
    return Gson().toJson(this)
}

/**
 * 价格格式是否正确(不可为0)
 */
fun String.isPrice(): Boolean {
    if (this.contains(Regex("^\\+?(?!0+(\\.00?)?\$)\\d+(\\.\\d\\d?)?\$"))
        && checkDecimalFirstNotZero(this)
    ) {
        return true
    }
    return false
}

/**
 * 检查首位数字是否为0，如 001.1 则不合法
 */
private fun checkDecimalFirstNotZero(price: String): Boolean {
    if (price.contains(".")) { //如果包含小数
        val sub = price.substringBefore(".") //取出小数前面的数字
        return if (sub.length >= 2 && sub.first().toString() != "0") { //当小数前面的数字长度大于2并且第一位不为0则符合规则
            true
        } else sub.length < 2 //如果.前面的数字长度小于2也则符合规则
    } else if (price.first().toString() != "0") {
        return true //不包含小数且第一位不为0则符合规则
    }
    return false
}

/**
 * 价格格式是否正确(可为0)
 */
fun String.isPriceWithout0(): Boolean {
    if (this.contains(Regex("^[0-9]*\$"))
        && checkDecimalFirstNot0Include0(this)
    ) {
        return true
    }
    return false
}

/**
 * 检查首位数字是否为0，如 001.1 则不合法，若只为0也合法
 */
private fun checkDecimalFirstNot0Include0(price: String): Boolean {
    if (price.contains(".")) { //如果包含小数
        val sub = price.substringBefore(".") //取出小数前面的数字
        return if (sub.length >= 2 && sub.first().toString() != "0") {
            true //当小数前面的数字长度大于2并且第一位不为0则符合规则
        } else sub.length < 2 //如果.前面的数字长度小于2也则符合规则
    } else if (price.length > 1) {
        if (price.first().toString() != "0") {
            return true //不包含小数且第一位不为0则符合规则
        }
    } else {
        //只有一位数字时都符合规则
        return true
    }
    return false
}

fun String.shortToast(gravity: Int = Gravity.CENTER){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#FF000000"))
        .setTextColor(Color.parseColor("#FFFFFF"))
        .setGravity(gravity,0,100)
        .setDurationIsLong(false)
        .show(this)
}

fun String.longToast(){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#FF000000"))
        .setTextColor(Color.parseColor("#FFFFFF"))
        .setGravity(Gravity.CENTER,0,100)
        .setDurationIsLong(true)
        .show(this)
}

/**
 * 格式化成131****0123
 * 隐藏中间四位
 */
fun String.replace2Hint():String {
    val sb = StringBuilder()
    if (this.length < 2){
//        ToastUtils.showShort("账号不能小于两位")
        return this
    }
    val startStr = this.substring(0,2)
//    val endStr = this.reversed().substring(0,2) //需要倒序
    val endStr = this.substring(this.length - 2,this.length) //不需要倒序
    sb.append(startStr)
    sb.append("****")
    sb.append(endStr)
    return sb.toString()
}

/**
 * 微信号 6-20位
 */
fun String.isWechat():Boolean = this.length in 6..20

/**
 * 字符串不为空
 */
inline fun CharSequence?.notNullAndEmptyAndBlank(no:String.()->Unit, nullOrEmptyOrBlank:String.()->Unit = {}) {
    if (this.isNullOrEmpty() || this.isNullOrBlank()) nullOrEmptyOrBlank.invoke(this.toString()) else no.invoke(this.toString())
}
