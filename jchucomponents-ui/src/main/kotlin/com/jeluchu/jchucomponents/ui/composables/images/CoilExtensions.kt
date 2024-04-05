package com.jeluchu.jchucomponents.ui.composables.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun String.remotetoPaiter(): Painter? =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(this)
            .size(Size.ORIGINAL)
            .build()
    )
        .state
        .painter

@Composable
fun CharSequence.remotetoPaiter(): Painter? =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(this)
            .size(Size.ORIGINAL)
            .build()
    )
        .state
        .painter