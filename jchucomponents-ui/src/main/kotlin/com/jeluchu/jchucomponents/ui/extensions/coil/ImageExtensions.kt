package com.jeluchu.jchucomponents.ui.extensions.coil

import android.content.Context
import android.graphics.Bitmap
import coil3.imageLoader
import coil3.request.CachePolicy
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap

suspend fun Context.getImageToBitmap(
    url: String,
    force: Boolean = false,
    isHardware: Boolean = false
): Bitmap {
    val request = ImageRequest.Builder(this).data(url).apply {
        if (force) {
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
        }
        allowHardware(isHardware)
    }.build()

    return when (val result = imageLoader.execute(request)) {
        is ErrorResult -> throw result.throwable
        is SuccessResult -> result.image.toBitmap()
    }
}