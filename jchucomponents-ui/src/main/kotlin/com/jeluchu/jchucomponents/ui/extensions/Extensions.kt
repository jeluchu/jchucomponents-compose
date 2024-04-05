package com.jeluchu.jchucomponents.ui.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

/** ---- BITMAPS ------------------------------------------------------------------------------- **/

fun Context.getBitmapFromVectorDrawable(
    width: Int?,
    height: Int?,
    drawableId: Int
): Bitmap {
    val drawable = ContextCompat.getDrawable(this, drawableId)
    val bitmap = Bitmap.createBitmap(
        width ?: drawable?.intrinsicWidth ?: 1,
        height ?: drawable?.intrinsicHeight ?: 1,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable?.setBounds(0, 0, canvas.width, canvas.height)
    drawable?.draw(canvas)
    return bitmap
}