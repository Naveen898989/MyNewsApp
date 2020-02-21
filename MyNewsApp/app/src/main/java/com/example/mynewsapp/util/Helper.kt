package com.example.mynewsapp.util

import android.content.Context
import android.net.ConnectivityManager
import com.example.mynewsapp.R
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun convertStringToElapsedTime(context: Context, time: String): String {
    val date = DATE_FORMAT.parse(time)

    val deltaTime = System.currentTimeMillis() - date!!.time

    if (deltaTime <= 60 * 1000) {
        return context.getString(R.string.time_moments_ago)
    } else if (deltaTime <= 60 * 60 * 1000) {
        val minutes = (deltaTime / 60 / 1000).toInt()

        return context.resources.getQuantityString(
            R.plurals.time_minutes_ago, minutes, minutes
        )
    } else if (deltaTime <= 24 * 60 * 60 * 1000) {
        val hours = (deltaTime / 60 / 60 / 1000).toInt()

        return context.resources.getQuantityString(
            R.plurals.time_hours_ago,
            hours, hours
        )
    } else {
        val days = (deltaTime / 24 / 60 / 60 / 1000).toInt()

        return context.resources.getQuantityString(
            R.plurals.time_days_ago, days, days
        )
    }
}
