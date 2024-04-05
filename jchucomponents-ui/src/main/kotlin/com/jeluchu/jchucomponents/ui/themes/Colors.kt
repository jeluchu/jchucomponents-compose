/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.themes

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
val primary = Color(0xFFA9D2B5)

@Stable
val secondary = Color(0xFF79BA98)

@Stable
val darkGreen = Color(0xFF4D7C63)

@Stable
val darkness = Color(0xFF333333)

@Stable
val milky = Color(0xFFF9F8DD)

val green200 = Color(0xffa5d6a7)
val green500 = Color(0xff4caf50)
val green700 = Color(0xff388e3c)

val artichoke = Color(0xFFA29574)
val cosmicLatte = Color(0xFFFEF8E4)

val blueBell = Color(0xFF9CA6D9)
val cell = Color(0xFF8793CC)
val darkPastelBlue = Color(0xFF7C8AC5)
val toolbox = Color(0xFF7280BF)
val glaucous = Color(0xFF6777B8)

fun toHex(color: String) = Color(android.graphics.Color.parseColor("#$color"))