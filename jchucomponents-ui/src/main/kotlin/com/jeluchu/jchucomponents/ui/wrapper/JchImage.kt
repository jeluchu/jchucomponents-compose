package com.jeluchu.jchucomponents.ui.wrapper

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
sealed interface JchImage {
    @Immutable @JvmInline value class Resource(@DrawableRes val id: Int): JchImage
    @Immutable @JvmInline value class DrawableImages( val image: Drawable): JchImage
    @Immutable @JvmInline value class Vector(val image: ImageVector): JchImage
    @Immutable @JvmInline value class Custom(val image: Painter): JchImage
}

/*
inline val JchImage.painter
    @Composable get() = when(this) {
        is JchImage.Resource -> id.toPainter()
        is JchImage.DrawableImages -> image.toBitmap().asImageBitmap().run { BitmapPainter(this) }
        is JchImage.Vector -> image
    }*/