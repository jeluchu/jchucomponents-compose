/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.accompanist.systemui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color

/**
 *
 * Author: @Jeluchu
 *
 * This component makes adjustments
 * to the system interface of the cell phone
 *
 * @param systemBarsColor change the color of the system navigation bar
 * @param statusBarColor change the color of the system notification bar
 *
 */

@Deprecated("Deprecated SystemStatusBarColors, replace with enableEdgeToEdge")
@Composable
fun SystemStatusBarColors(
    statusBarColor: Color,
    systemBarsColor: Color,
    useDarkIcons: Boolean = !isSystemInDarkTheme()
) = with(rememberSystemUiController()) {
    SideEffect {
        setSystemBarsColor(
            color = systemBarsColor,
            darkIcons = useDarkIcons
        )
        setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }
}