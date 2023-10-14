package com.mayann.itunesmobile.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@SuppressLint("SimpleDateFormat")
val outputDateHourTimeFormat = SimpleDateFormat("MMMM dd, h:mm a")
val dateTimeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

const val TIME_ZONE_ASIA_MANILA = "Asia/Manila"

fun getPHTimeZone(): ZoneId {
    return ZoneId.of(TIME_ZONE_ASIA_MANILA)
}

fun String.prettyFormatTime(): String {
    val dateLocalTime = ZonedDateTime.parse(this).withZoneSameInstant(getPHTimeZone())
    val dateConverted = Date.from(dateLocalTime.toInstant())
    return outputDateHourTimeFormat.format(dateConverted).uppercase(word = "am")
        .uppercase(word = "pm")
}

fun getCurrentTime(): String {
    val currentDateTime = ZonedDateTime.now(getPHTimeZone())
    return dateTimeFormat.format(currentDateTime)
}
