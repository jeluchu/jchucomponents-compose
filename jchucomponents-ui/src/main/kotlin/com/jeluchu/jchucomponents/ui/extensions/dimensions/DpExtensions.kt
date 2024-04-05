package com.jeluchu.jchucomponents.ui.extensions.dimensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

inline val Dp.px: Float
    @Composable @ReadOnlyComposable get() = LocalDensity.current.run { toPx() }

inline val Dp.roundToPx: Int
    @Composable @ReadOnlyComposable get() = LocalDensity.current.run { roundToPx() }