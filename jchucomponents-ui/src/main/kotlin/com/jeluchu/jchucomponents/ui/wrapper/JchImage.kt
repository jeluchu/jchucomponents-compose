package com.jeluchu.jchucomponents.ui.wrapper

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.core.graphics.drawable.toBitmap
import com.jeluchu.jchucomponents.ui.extensions.toImageVector
import com.jeluchu.jchucomponents.ui.extensions.toPainter
import com.jeluchu.jchucomponents.ui.R

/**
 *
 * Author: @Jeluchu
 *
 * A more generic way to accept in any component any type
 * of image passed by parameter. Subsequently you can choose
 * to receive a painter [Painter] or an imageVector [ImageVector]
 *
 * Types:
 *
 * [JchImage.Vector]
 * [JchImage.Custom]
 * [JchImage.Resource]
 * [JchImage.DrawableImages]
 *
 */
@Immutable
sealed interface JchImage {
    @Immutable @JvmInline value class Custom(val image: Painter): JchImage
    @Immutable @JvmInline value class Vector(val image: ImageVector): JchImage
    @Immutable @JvmInline value class Resource(@DrawableRes val id: Int): JchImage
    @Immutable @JvmInline value class DrawableImages( val image: Drawable): JchImage
}

/**
 *
 * Author: @Jeluchu
 *
 * It will serve to obtain from any type supported by JchImage a [Painter]
 *
 * Types supported:
 *
 * [JchImage.Vector]
 * [JchImage.Custom]
 * [JchImage.Resource]
 * [JchImage.DrawableImages]
 *
 */
inline val JchImage.painter
    @Composable get() = when(this) {
        is JchImage.Custom -> image
        is JchImage.Resource -> id.toPainter()
        is JchImage.Vector -> rememberVectorPainter(image = image)
        is JchImage.DrawableImages -> image.toBitmap().asImageBitmap().run { BitmapPainter(this) }
    }

/**
 *
 * Author: @Jeluchu
 *
 * It will serve to obtain from any type supported by JchImage a [ImageVector]
 *
 * Types supported:
 *
 * [JchImage.Vector]
 * [JchImage.Resource]
 *
 * In the other cases, a default vector will be returned
 * that is not the one passed by the user from JchImage
 * That will happen in the following types:
 *
 * [JchImage.Custom]
 * [JchImage.DrawableImages]
 *
 */
inline val JchImage.imageVector
    @Composable get() = when(this) {
        is JchImage.Vector -> image
        is JchImage.Resource -> id.toImageVector()
        is JchImage.Custom -> R.drawable.ic_deco_jeluchu.toImageVector()
        is JchImage.DrawableImages -> R.drawable.ic_deco_jeluchu.toImageVector()
    }