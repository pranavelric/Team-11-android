package com.hacka.team11.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import java.io.IOException
import java.io.InputStream


fun checkAboveOreo(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.O
}

fun checkAboveKitkat(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT
}

fun checkAboveLollipop(): Boolean {
    return   Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP
}

fun Context.rateUs() {

    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("amzn://apps/android?p=$packageName")
            )
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=$packageName")
            )
        )
    }

}




fun inputStreamToString(inputStream: InputStream): String {
    return try {
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        String(bytes)
    } catch (e: IOException) {
        null
    }.toString()
}
