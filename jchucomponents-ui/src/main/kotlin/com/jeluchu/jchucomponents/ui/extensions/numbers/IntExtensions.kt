package com.jeluchu.jchucomponents.ui.extensions.numbers

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

inline val Int.dpToSp: TextUnit
    @Composable @ReadOnlyComposable get() = LocalDensity.current.run { dp.toSp() }

@SuppressLint("ComposableNaming")
@Composable
fun Int.height() = Spacer(modifier = Modifier.height(this.dp))

@SuppressLint("ComposableNaming")
@Composable
fun Int.width() = Spacer(modifier = Modifier.width(this.dp))