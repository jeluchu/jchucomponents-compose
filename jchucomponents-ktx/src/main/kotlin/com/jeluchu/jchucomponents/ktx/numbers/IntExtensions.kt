/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ktx.numbers

import com.jeluchu.jchucomponents.ktx.constants.FIRST_DAY_OF_MONTH
import com.jeluchu.jchucomponents.ktx.strings.empty
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Calendar
import kotlin.math.roundToInt

fun Int.Companion.empty() = 0
fun Int?.orEmpty(defaultValue: Int = Int.empty()) = this ?: defaultValue
fun Int.isNotEmpty() = this != Int.empty()
fun Long.bytesToMeg(): String = (this / (1024L * 1024L)).toString()

fun Float.Companion.empty() = 0f
fun Float?.orEmpty(defaultValue: Float = Float.empty()): Float = this ?: defaultValue

fun Long.Companion.empty() = 0L
fun Long?.orEmpty(defaultValue: Long = Long.empty()): Long = this ?: defaultValue

fun Int.milliSecondsToTimer(): String {

    var finalTimerString = String.empty()
    val secondsString: String

    val hours = (this / (1000 * 60 * 60))
    val minutes = (this % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = (this % (1000 * 60 * 60) % (1000 * 60) / 1000)
    if (hours > 0) finalTimerString = "$hours:"

    secondsString = if (seconds < 10) {
        "0$seconds"
    } else {
        "" + seconds
    }
    finalTimerString = "$finalTimerString$minutes:$secondsString"

    return finalTimerString
}

fun Int?.roundUpToNearestTen(): Int = ((((this ?: 0) + 5) / 10.0).roundToInt() * 10)

/**
 * Return a list of calendar values which contains the months of the year.
 * If the year is the current one, return only the months until the current one.
 */
fun Int.getMonths(): List<Calendar> =
    mutableListOf<Calendar>().also { months ->
        Calendar.getInstance().let { calendar ->
            val maxMonth =
                if (this == calendar.get(Calendar.YEAR)) calendar.get(Calendar.MONTH)
                else Calendar.DECEMBER

            calendar.set(Calendar.YEAR, this)

            for (i in Calendar.JANUARY..maxMonth)
                months.add(
                    Calendar.getInstance().apply {
                        set(Calendar.YEAR, calendar.get(Calendar.YEAR))
                        set(Calendar.MONTH, i)
                        set(Calendar.DAY_OF_MONTH, FIRST_DAY_OF_MONTH)
                    }
                )
        }
    }

fun Int.thousandsFormat(): String {
    return try {
        val df = DecimalFormat("#,##0", DecimalFormatSymbols().apply { groupingSeparator = '.' }).apply { groupingSize = 3 }
        df.format(this)
    } catch (e: Exception) {
        toString()
    }
}