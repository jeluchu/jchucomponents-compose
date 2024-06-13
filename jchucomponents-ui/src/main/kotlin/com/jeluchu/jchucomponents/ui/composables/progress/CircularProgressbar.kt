/*
 *
 *  Copyright 2018-2023 by Jeluchu <jelu@jeluchu.com>
 *
 */

package com.jeluchu.jchucomponents.ui.composables.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ui.extensions.toImageVector
import com.jeluchu.jchucomponents.ui.extensions.toPainter
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.column.ScrollableColumn
import com.jeluchu.jchucomponents.ui.extensions.modifier.noRippleClickable
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf
import com.jeluchu.jchucomponents.ui.themes.artichoke

/**
 *
 * [LinearProgressbar]
 *
 * @param size [Dp] size of the CircularProgressBar
 * @param icon [ImageVector] the value we want in case the Bitmap is null,
 * by default it will be an empty one
 * @param enabled [Boolean] Enables or disables the content, showing a colour,
 * indicating that it is not active
 * @param number [Float] The current progress number
 * @param maxNumber [Float] The maximum amount of progress that can be achieved
 * @param indicatorThickness [Dp] Progress bar height
 * @param circularProgressCustom [CircularProgressCustom] Progress bar elements and icon
 * customisations
 * @param animationDuration [Int] Duration of the initial animation
 * @param animationDelay [Int] Time delay in the initial animation duration
 *
 */
@Composable
fun CircularProgressbar(
    size: Dp = 45.dp,
    icon: ImageVector,
    number: Float = 5f,
    maxNumber: Float = 10f,
    enabled: Boolean = true,
    animationDelay: Int = 0,
    animationDuration: Int = 1000,
    indicatorThickness: Dp = 10.dp,
    circularProgressCustom: CircularProgressCustom = CircularProgressCustom(),
    onClick: (MutableState<Float>, Float) -> Unit = { _, _ -> }
) {
    val checkMaxValue = if (number > maxNumber) maxNumber else number
    val numberTimes = rememberMutableStateOf(key1 = checkMaxValue, value = checkMaxValue)
    val animateNumber = animateFloatAsState(
        targetValue = numberTimes.value,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    Box(
        modifier = Modifier
            .padding(5.dp)
            .size(size = size)
            .noRippleClickable { onClick(numberTimes, maxNumber) },
        contentAlignment = Alignment.Center,
    ) {
        CircularBar(
            size = size,
            enabled = enabled,
            maxNumber = maxNumber,
            numberTimes = numberTimes.value,
            animateNumber = animateNumber.value,
            indicatorThickness = indicatorThickness,
            circularProgressCustom = circularProgressCustom
        )

        Icon(
            modifier = Modifier.size(25.dp),
            imageVector = icon,
            tint = when {
                !enabled -> circularProgressCustom.disabledIndicator
                numberTimes.value != maxNumber -> circularProgressCustom.iconTint
                else -> circularProgressCustom.foregroundIndicatorComplete
            },
            contentDescription = String.empty()
        )
    }
}

/**
 *
 * [LinearProgressbar]
 *
 * @param size [Dp] size of the CircularProgressBar
 * @param icon [Painter] the value we want in case the Bitmap is null,
 * by default it will be an empty one
 * @param enabled [Boolean] Enables or disables the content, showing a colour,
 * indicating that it is not active
 * @param number [Float] The current progress number
 * @param maxNumber [Float] The maximum amount of progress that can be achieved
 * @param indicatorThickness [Dp] Progress bar height
 * @param circularProgressCustom [CircularProgressCustom] Progress bar elements and icon
 * customisations
 * @param animationDuration [Int] Duration of the initial animation
 * @param animationDelay [Int] Time delay in the initial animation duration
 *
 */
@Composable
fun CircularProgressbar(
    icon: Painter,
    size: Dp = 45.dp,
    number: Float = 5f,
    maxNumber: Float = 10f,
    enabled: Boolean = true,
    animationDelay: Int = 0,
    animationDuration: Int = 1000,
    indicatorThickness: Dp = 10.dp,
    circularProgressCustom: CircularProgressCustom = CircularProgressCustom(),
    onClick: (MutableState<Float>, Float) -> Unit = { _, _ -> }
) {
    val checkMaxValue = if (number > maxNumber) maxNumber else number
    val numberTimes = rememberMutableStateOf(key1 = checkMaxValue, value = checkMaxValue)
    val animateNumber = animateFloatAsState(
        targetValue = numberTimes.value,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    Box(
        modifier = Modifier
            .padding(5.dp)
            .size(size = size)
            .noRippleClickable { onClick(numberTimes, maxNumber) },
        contentAlignment = Alignment.Center,
    ) {
        CircularBar(
            size = size,
            enabled = enabled,
            maxNumber = maxNumber,
            numberTimes = numberTimes.value,
            animateNumber = animateNumber.value,
            indicatorThickness = indicatorThickness,
            circularProgressCustom = circularProgressCustom
        )

        Image(
            modifier = Modifier.size(25.dp),
            painter = icon,
            contentDescription = null
        )
    }
}

@Composable
fun CircularBar(
    size: Dp,
    enabled: Boolean,
    maxNumber: Float,
    numberTimes: Float,
    animateNumber: Float,
    indicatorThickness: Dp,
    circularProgressCustom: CircularProgressCustom,
) = Canvas(modifier = Modifier.size(size = size)) {
    val sweepAngle = (animateNumber / maxNumber) * 360

    drawCircle(
        color = if (enabled) circularProgressCustom.backgroundIndicator
        else circularProgressCustom.disabledBackground,
        radius = size.toPx() / 2,
        style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round)
    )

    drawArc(
        color = when {
            !enabled -> circularProgressCustom.disabledIndicator
            numberTimes != maxNumber -> circularProgressCustom.foregroundIndicator
            else -> circularProgressCustom.foregroundIndicatorComplete
        },
        startAngle = -90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(indicatorThickness.toPx(), cap = StrokeCap.Round)
    )
}

@Immutable
class CircularProgressCustom constructor(
    val disabledIndicator: Color = Color.Gray,
    val disabledBackground: Color = Color.LightGray,
    val backgroundIndicator: Color = Color.LightGray.copy(alpha = 0.3f),
    val foregroundIndicator: Color = Color(0xFF35898f),
    val foregroundIndicatorComplete: Color = Color(0xFF7DA88C),
    val iconTint: Color = artichoke
)

@Preview(showBackground = true)
@Composable
fun CircularProgressbarPreview() {
    ScrollableColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "CircularProgressbar with ImageVector")
        Text(text = "Disable")
        CircularProgressbar(
            icon = R.drawable.ic_btn_share.toImageVector(),
            enabled = false
        )

        Text(text = "Enabled")
        CircularProgressbar(
            number = 0f,
            icon = R.drawable.ic_btn_share.toImageVector()
        )

        Text(text = "When the number is less than the maximum")
        CircularProgressbar(
            icon = R.drawable.ic_btn_share.toImageVector(),
            number = 400f,
            maxNumber = 1000f
        )

        Text(text = "When the number is equal to the maximum")
        CircularProgressbar(
            icon = R.drawable.ic_btn_share.toImageVector(),
            number = 1000f,
            maxNumber = 1000f
        )

        Text(text = "When the number is greater than the maximum")
        CircularProgressbar(
            icon = R.drawable.ic_btn_share.toImageVector(),
            number = 2000f,
            maxNumber = 1000f
        )

        Text(text = "CircularProgressbar with Painter")
        Text(text = "Disable")
        CircularProgressbar(
            icon = R.drawable.ic_deco_jeluchu.toPainter(),
            enabled = false
        )

        Text(text = "Enabled")
        CircularProgressbar(
            number = 0f,
            icon = R.drawable.ic_deco_jeluchu.toPainter()
        )

        Text(text = "When the number is less than the maximum")
        CircularProgressbar(
            number = 400f,
            maxNumber = 1000f,
            icon = R.drawable.ic_deco_jeluchu.toPainter()
        )

        Text(text = "When the number is equal to the maximum")
        CircularProgressbar(
            number = 1000f,
            maxNumber = 1000f,
            icon = R.drawable.ic_deco_jeluchu.toPainter()
        )

        Text(text = "When the number is greater than the maximum")
        CircularProgressbar(
            number = 2000f,
            maxNumber = 1000f,
            icon = R.drawable.ic_deco_jeluchu.toPainter()
        )
    }
}