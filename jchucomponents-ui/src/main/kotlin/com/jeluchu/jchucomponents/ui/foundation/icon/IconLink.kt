@file:Suppress("unused")

package com.jeluchu.jchucomponents.ui.foundation.icon

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 *
 * Icon component that draws [bitmap] using [tint], defaulting to [LocalContentColor]
 *
 * @param bitmap [ImageBitmap] to draw inside this Icon
 * @param contentDescription [StringRes] used by accessibility services to describe what this icon
 * represents
 *
 * This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take
 *
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [bitmap]
 *
 * If [Color.Unspecified] is provided, then no
 * tint is applied
 *
 */
@Composable
@NonRestartableComposable
fun IconLink(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit
) = IconButton(onClick = onClick) {
    Icon(
        tint = tint,
        bitmap = bitmap,
        modifier = modifier,
        contentDescription = contentDescription
    )
}

/**
 *
 * Icon component that draws [imageVector] using [tint], defaulting to [LocalContentColor]
 *
 * @param imageVector [ImageVector] to draw inside this Icon
 * @param contentDescription [StringRes] used by accessibility services to describe what this icon
 * represents
 *
 * This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take
 *
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [imageVector]
 *
 * If [Color.Unspecified] is provided, then no
 * tint is applied
 *
 */
@Composable
@NonRestartableComposable
fun IconLink(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit
) = IconButton(onClick = onClick) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 *
 * Icon component that draws a [painter] using [tint], defaulting to [LocalContentColor].
 *
 * @param painter [Painter] to draw inside this Icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents
 *
 * This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take
 *
 * This text should be localized, such as by using [androidx.compose.ui.res.stringResource]
 * or similar
 *
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [painter]
 *
 * If [Color.Unspecified] is provided, then no
 * tint is applied
 *
 */
@Composable
fun IconLink(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit
) = IconButton(onClick = onClick) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

/**
 *
 * Icon component that draws [activeImageVector] or [disableImageVector] using [tint],
 * defaulting to [LocalContentColor]
 *
 * @param modifier optional [Modifier] for this Icon
 * @param isActive [Boolean] will show one icon or another depending on whether it is active or not
 * @param activeImageVector [ImageVector] to draw inside this Icon
 * @param disableImageVector [ImageVector] to draw inside this Icon
 * represents
 *
 * This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take
 *
 * @param tint tint to be applied to [activeImageVector] or [disableImageVector]
 *
 * If [Color.Unspecified] is provided, then no
 * tint is applied
 *
 */
@Composable
fun IconLink(
    isActive: Boolean,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
    activeImageVector: ImageVector,
    disableImageVector: ImageVector,
    onClick: () -> Unit
) = IconButton(onClick = onClick) {
    Icon(
        modifier = modifier,
        imageVector = if (isActive) activeImageVector else disableImageVector,
        contentDescription = contentDescription,
        tint = tint
    )
}