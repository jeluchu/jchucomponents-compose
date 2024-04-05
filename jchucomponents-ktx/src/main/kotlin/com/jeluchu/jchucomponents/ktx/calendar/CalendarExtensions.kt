package com.jeluchu.jchucomponents.ktx.calendar

import java.util.Calendar

var Calendar.year: Int
    get() = get(Calendar.YEAR)
    set(value) {
        set(Calendar.YEAR, value)
    }

var Calendar.month: Int
    get() = get(Calendar.MONTH)
    set(value) {
        set(Calendar.MONTH, value)
    }

var Calendar.day: Int
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) {
        set(Calendar.DAY_OF_MONTH, value)
    }

fun Calendar.previousYear() = if (get(Calendar.MONTH) == Calendar.JANUARY) get(Calendar.YEAR) - 2
else get(Calendar.YEAR) - 1

fun Calendar.previousMonth() = if (get(Calendar.MONTH) == Calendar.JANUARY) Calendar.DECEMBER
else get(Calendar.MONTH) - 1

fun Calendar.nextMonth() = if (get(Calendar.MONTH) == Calendar.DECEMBER) Calendar.JANUARY
else get(Calendar.MONTH) + 1

fun Calendar.setLastDayOfMonth() = apply {
    add(Calendar.MONTH, 1)
    set(Calendar.DAY_OF_MONTH, 1)
    add(Calendar.DAY_OF_YEAR, -1)
}

fun Calendar.setLastDayOfYear() = apply {
    add(Calendar.YEAR, 1)
    set(Calendar.DAY_OF_YEAR, 1)
    add(Calendar.DAY_OF_YEAR, -1)
}