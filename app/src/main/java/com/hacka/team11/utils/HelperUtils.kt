package com.hacka.team11.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.alarm.momentix.utils.toast
import com.hacka.team11.BuildConfig
import java.io.IOException
import java.io.InputStream


fun checkAboveOreo(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.O
}

fun checkAboveKitkat(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT
}

fun checkAboveLollipop(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP
}

fun Context.rateUs() {
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    ("market://details?id=$packageName")
                )
            )
        )
    } catch (e1: ActivityNotFoundException) {
        try {
            this.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                )
            )
        } catch (e2: ActivityNotFoundException) {

            toast("You don't have any app that can open this link",)
        }
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
