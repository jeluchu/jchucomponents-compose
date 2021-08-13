package com.jeluchu.jchucomponentscompose.core.extensions.time

import java.util.*

fun currentDayWeekString(): Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
fun currentDayMonthString(): Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
fun currentMonthString(): Int = Calendar.getInstance().get(Calendar.MONTH)
fun currentYearString(): String = Calendar.getInstance().get(Calendar.YEAR).toString()

fun isFetchFiveMinutes(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 5 * 60 * 1000

fun isFetchThirtyMinutes(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 30 * 60 * 1000

fun isFetchSixHours(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 6 * 60 * 60 * 1000

fun isFetchTwelveHours(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 12 * 60 * 60 * 1000

fun isFetchOneDay(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 24 * 60 * 60 * 1000

fun isFetchTwoDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 48 * 60 * 60 * 1000

fun isFetchThreeDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 72 * 60 * 60 * 1000

fun isFetchFourDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 96 * 60 * 60 * 1000

fun isFetchFiveDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 120 * 60 * 60 * 1000

fun isFetchSixDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 144 * 60 * 60 * 1000

fun isFetchSevenDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 144 * 60 * 60 * 1000

fun isFetchTenDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 240 * 60 * 60 * 1000

fun isFetchFifteenDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 360 * 60 * 60 * 1000

fun isFetchTwentyDays(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 480 * 60 * 60 * 1000