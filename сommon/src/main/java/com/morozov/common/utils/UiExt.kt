package com.morozov.common.utils


import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


fun String?.toCustomDateFormat(): String {
    if (this.isNullOrBlank()) {
        return ""
    }

    return try {
        val formatter = DateTimeFormatter.ofPattern("M/d/yyyy")
        val date = LocalDate.parse(this, formatter)

        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val month = date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val dayOfMonth = date.dayOfMonth
        val year = date.year % 100

        "$dayOfWeek, $month $dayOfMonth, '$year"
    } catch (ex: Exception) {
        ""
    }
}

