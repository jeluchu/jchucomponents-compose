/*
*
*  Copyright 2022 Jeluchu
*
*/

package com.jeluchu.jchucomponents.ui.extensions.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf
import kotlinx.coroutines.delay
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Calendar

/**
 *
 * Author: @Jeluchu
 *
 * This component only returns the current time (it is a kind of clock)
 * which is updated every minute.
 *
 */

@Composable
fun minutesLeft(): String {

    var time by rememberMutableStateOf(currentTime())

    LaunchedEffect(0) {
        while (true) {
            time = currentTime()
            delay(1000)
        }
    }

    return "${time.hours}:${time.minutes} ${time.zoneDay}"
}

fun currentTime(): Time {
    val cal = Calendar.getInstance()
    return Time(
        hours = cal.get(Calendar.HOUR_OF_DAY).fixedDecimalsTime(),
        minutes = cal.get(Calendar.MINUTE).fixedDecimalsTime(),
        zoneDay = if (cal.get(Calendar.AM_PM) == 0) "AM" else "PM"
    )
}

fun Int.fixedDecimalsTime(): String {
    val decimalFormat = DecimalFormat("00")
    decimalFormat.roundingMode = RoundingMode.DOWN
    return decimalFormat.format(this)
}

data class Time(
    var hours: String,
    var minutes: String,
    var zoneDay: String
)
