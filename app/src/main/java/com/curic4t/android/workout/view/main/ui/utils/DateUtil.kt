package com.curic4t.android.workout.view.main.ui.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Singleton

@Singleton
class DateUtil {
    fun getCurrentDate(isNoHyphen: Boolean): String {
        val format = if (isNoHyphen) "yyyyMMdd" else "yyyy-MM-dd"
        return if (Build.VERSION.SDK_INT >= 26) {
            val current = LocalDateTime.now()
            //return current.format(DateTimeFormatter.BASIC_ISO_DATE)
            current.format(DateTimeFormatter.ofPattern(format))

        } else {
            val now = System.currentTimeMillis()
            val date = Date(now)
            SimpleDateFormat(format, Locale.getDefault()).format(date)

        }
    }

    fun getCurrentTime(): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            val current = LocalDateTime.now()
            current.format(DateTimeFormatter.ofPattern("HHmm"))

        } else {
            val now = System.currentTimeMillis()
            val date = Date(now)
            SimpleDateFormat("HHmm", Locale.getDefault()).format(date)
        }
    }

}