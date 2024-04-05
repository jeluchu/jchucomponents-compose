/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.foundation.canva

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import com.jeluchu.jchucomponents.ui.extensions.getBitmapFromVectorDrawable

@Composable
fun CanvasBackground(
    modifier: Modifier = Modifier,
    with: Int,
    height: Int,
    drawable: Int
) {

    val context = LocalContext.current

    Canvas(
        modifier = modifier
    ) {

        val pattern =
            context.getBitmapFromVectorDrawable(
                with, height,
                drawable
            ).asImageBitmap()

        val paint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            shader = ImageShader(pattern, TileMode.Repeated, TileMode.Repeated)
        }

        drawIntoCanvas {
            it.nativeCanvas.drawPaint(paint)
        }
        paint.reset()
    }

}