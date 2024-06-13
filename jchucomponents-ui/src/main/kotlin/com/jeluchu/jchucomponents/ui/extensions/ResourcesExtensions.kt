package com.jeluchu.jchucomponents.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource

@Composable
@ReadOnlyComposable
fun Int.toStringRes() = stringResource(this)

@Composable
@ReadOnlyComposable
fun Int.toStringArrayRes() = stringArrayResource(this)

@Composable
fun Int.toImageVector() = ImageVector.vectorResource(this)

@Composable
fun Int.toPainter() = painterResource(this)

@Composable
fun Int.toColor() = colorResource(this)