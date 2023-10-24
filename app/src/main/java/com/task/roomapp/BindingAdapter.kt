package com.task.roomapp

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object CustomBindingAdapters {

    @BindingAdapter("app:textData", "app:checkData", requireAll = true)
    @JvmStatic
    fun setFocus(editText: EditText, value: String, checked: Boolean?) {
        if (value != "") {
            editText.isFocusable = false
            editText.isClickable = false
        }
    }

    @BindingAdapter("app:utcTime")
    @JvmStatic
    fun setUtcTime(textView: TextView, timestamp: Long?) {
        timestamp?.let {
            val utcFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US)
            utcFormatter.timeZone = TimeZone.getTimeZone("UTC")
            val utcTime = utcFormatter.format(Date(timestamp))
            textView.text = utcTime
        }
    }

}