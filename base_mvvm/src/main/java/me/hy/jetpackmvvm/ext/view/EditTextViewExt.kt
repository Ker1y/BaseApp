package me.hy.jetpackmvvm.ext.view

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import me.hy.jetpackmvvm.ext.MoneyInputFilter
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 优化输入框
 */
fun EditText.afterTextChange(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}


/**
 * 获取文本
 */
fun EditText.textString(): String {
    return this.text.toString()
}
/**
 * 获取去除空字符串的文本
 */
fun EditText.textStringTrim(): String {
    return this.textString().trim()
}
/**
 * 文本是否为空
 */
fun EditText.isEmpty(): Boolean {
    return this.textString().isEmpty()
}
/**
 * 去空字符串后文本是否为空
 */
fun EditText.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}
/**
 * 获取文本
 */
fun TextView.textString(): String {
    return this.text.toString()
}
/**
 * 获取去除空字符串的文本
 */
fun TextView.textStringTrim(): String {
    return this.textString().trim()
}
/**
 * 文本是否为空
 */
fun TextView.isEmpty(): Boolean {
    return this.textString().isEmpty()
}
/**
 * 去空字符串后文本是否为空
 */
fun TextView.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}


/**
 *  设置EditText输入只能为价格格式
 */

fun EditText.setMoneyMode() {
    filters = arrayOf(MoneyInputFilter())
}

/**
 * 禁止EditText输入空格和换行符
 *
 * @param length 需要限制输入的长度
 */
fun EditText.setNoInputSpace(length: Int) {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }
    this.filters = arrayOf(filter, InputFilter.LengthFilter(length))
}

fun EditText.setNoInputSpace() {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }
    this.filters = arrayOf(filter)
}



/**
 * 禁止EditText输入特殊字符
 *
 * @param editText EditText输入框
 */
fun EditText.setEditTextInputSpeChat(editText: EditText) {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        val speChat =
            "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"
        val pattern: Pattern = Pattern.compile(speChat)
        val matcher: Matcher = pattern.matcher(source.toString())
        if (matcher.find()) {
            ""
        } else {
            null
        }
    }
    editText.filters = arrayOf(filter)
}