/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ktx.bitmaps

import android.graphics.Bitmap
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.jeluchu.jchucomponents.ktx.constants.SIZE_2MB_BYTES
import java.io.ByteArrayOutputStream
import kotlin.math.floor
import kotlin.math.sqrt


/**
 *
 * [Bitmap] Extension to show the original [Bitmap] that we pass from
 * the extension or in case of being null we will pass a default value.
 * This default value can be the value you want or the defined one
 * (this would be an empty one)
 *
 * @param defaultValue [Bitmap] the value we want in case the Bitmap is null,
 * by default it will be an empty one
 *
 */
fun Bitmap?.orEmpty(
    defaultValue: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
): Bitmap = this ?: defaultValue

/**
 *
 * [ImageBitmap] Extension to show the original [ImageBitmap] that we pass from
 * the extension or in case of being null we will pass a default value.
 * This default value can be the value you want or the defined one
 * (this would be an empty one)
 *
 * @param defaultValue [ImageBitmap] the value we want in case the Bitmap is null,
 * by default it will be an empty one
 *
 * @see androidx.compose.ui.graphics.ImageBitmap
 *
 */
fun ImageBitmap?.orEmpty(
    defaultValue: ImageBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).asImageBitmap()
): ImageBitmap = this ?: defaultValue

/**
 * Bitmap to base 64.
 *
 * @param bitmap the bitmap
 * @return the string
 */
fun Bitmap.toBase64(): String? {
    val byteArrayOutputStream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun Bitmap.scaleBitmap(maxBytes: Long = SIZE_2MB_BYTES.toLong()): Bitmap? {
    val currentWidth = this.width
    val currentHeight = this.height
    val currentPixels = currentWidth * currentHeight
    val maxPixels = maxBytes / 4
    if (currentPixels <= maxPixels) return this
    val scaleFactor = sqrt(maxPixels / currentPixels.toDouble())
    val newWidthPx = floor(currentWidth * scaleFactor).toInt()
    val newHeightPx = floor(currentHeight * scaleFactor).toInt()
    return Bitmap.createScaledBitmap(this, newWidthPx, newHeightPx, true)
}