package com.jeluchu.jchucomponents.ui.composables.images

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size
import kotlinx.coroutines.flow.collect

@Composable
fun String.remotetoPaiter(): Painter? =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(this)
            .size(Size.ORIGINAL)
            .build()
    )
        .state
        .collectAsStateWithLifecycle()
        .value
        .painter

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CharSequence.remotetoPaiter(): Painter? =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(this)
            .size(Size.ORIGINAL)
            .build()
    )
        .state
        .collectAsStateWithLifecycle()
        .value
        .painter