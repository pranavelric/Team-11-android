package com.hacka.team11.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun getForamttedTimeFromStrgin(time: String): String {
    var formattedDate = ""
    if (checkAboveOreo()) {
        val inputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MMM-yyy", Locale.ENGLISH)

        try {
            val date: LocalDate = LocalDate.parse(time, inputFormatter)
            formattedDate = outputFormatter.format(date)
        } catch (e: Exception) {
            formattedDate = time
        }

        return formattedDate
    } else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MMM-yyyy")
        val date: Date = inputFormat.parse(time)
        val formattedDate: String = outputFormat.format(date)
        return formattedDate
    }
}

fun getMilliSecondsOfDate(time: String): Long {
    if (checkAboveOreo()) {
        val inputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)

        val date: LocalDateTime = LocalDateTime.parse(time, inputFormatter)
        val millis = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        return millis
    } else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val date: Date = inputFormat.parse(time)
        val millis = date.time
        return millis

    }

}

