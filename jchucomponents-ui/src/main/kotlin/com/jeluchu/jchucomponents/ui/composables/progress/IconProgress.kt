package com.jeluchu.jchucomponents.ui.composables.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.colors.toColorFilter
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ktx.compose.toPainter
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf
import com.jeluchu.jchucomponents.ui.themes.cosmicLatte

@Composable
fun IconProgress(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    enabled: Boolean = true,
    number: Float = 1000f,
    maxNumber: Float = 1000f,
    indicatorHeight: Dp = 8.dp,
    iconProgressCustom: IconProgressCustom = IconProgressCustom(),
    iconProgressCounter: IconProgressCounter = IconProgressCounter(),
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    style: TextStyle = LocalTextStyle.current
) {
    val checkMaxValue = if (number > maxNumber) maxNumber else number
    val numberTimes by rememberMutableStateOf(key1 = checkMaxValue, value = checkMaxValue)
    val animateNumber by animateFloatAsState(
        targetValue = if (numberTimes > maxNumber) maxNumber else numberTimes,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    Column(
        modifier = modifier
            .clip(10.cornerRadius())
            .background(iconProgressCustom.container)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        androidx.compose.material3.Icon(
            modifier = Modifier
                .size(iconProgressCustom.iconSize)
                .padding(iconProgressCustom.iconPadding),
            imageVector = icon,
            tint = when {
                !enabled -> iconProgressCounter.disabledIndicator
                numberTimes != maxNumber -> iconProgressCustom.content
                else -> iconProgressCustom.foregroundIndicatorComplete
            },
            contentDescription = String.empty()
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(indicatorHeight)
        ) {
            drawLine(
                color = if (enabled) iconProgressCustom.containerIndicator
                else iconProgressCustom.disabledIndicator,
                cap = StrokeCap.Round,
                strokeWidth = size.height,
                start = Offset(x = 0f, y = center.y),
                end = Offset(x = size.width, y = center.y)
            )

            if (enabled) {
                val progress = (animateNumber / maxNumber) * size.width
                if (animateNumber != 0f)
                    drawLine(
                        color = if (numberTimes != maxNumber) iconProgressCustom.foregroundIndicator
                        else iconProgressCustom.foregroundIndicatorComplete,
                        cap = StrokeCap.Round,
                        strokeWidth = size.height,
                        start = Offset(x = 0f, y = center.y),
                        end = Offset(x = progress, y = center.y)
                    )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .clip(iconProgressCounter.shape.cornerRadius())
                .background(
                    if (enabled) iconProgressCounter.container
                    else iconProgressCounter.disabledIndicator
                )
                .padding(horizontal = 15.dp),
            text = if (enabled) "${numberTimes.toInt()}/${maxNumber.toInt()}" else "- / -",
            style = style,
            textAlign = TextAlign.Center,
            color = iconProgressCounter.content
        )
    }
}

@Composable
fun IconProgress(
    modifier: Modifier = Modifier,
    icon: Painter,
    enabled: Boolean = true,
    number: Float = 1000f,
    maxNumber: Float = 1000f,
    indicatorHeight: Dp = 8.dp,
    iconProgressCustom: IconProgressCustom = IconProgressCustom(),
    iconProgressCounter: IconProgressCounter = IconProgressCounter(),
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    style: TextStyle = LocalTextStyle.current
) {
    val checkMaxValue = if (number > maxNumber) maxNumber else number
    val numberTimes by rememberMutableStateOf(key1 = checkMaxValue, value = checkMaxValue)
    val animateNumber by animateFloatAsState(
        targetValue = if (numberTimes > maxNumber) maxNumber else numberTimes,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    Column(
        modifier = modifier
            .clip(10.cornerRadius())
            .background(iconProgressCustom.container)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            modifier = Modifier
                .size(iconProgressCustom.iconSize)
                .padding(iconProgressCustom.iconPadding),
            painter = icon,
            colorFilter = when {
                !enabled -> iconProgressCounter.disabledIndicator
                numberTimes != maxNumber -> iconProgressCustom.container
                else -> iconProgressCustom.foregroundIndicatorComplete
            }.toColorFilter(),
            contentDescription = null
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(indicatorHeight)
        ) {
            drawLine(
                color = if (enabled) iconProgressCustom.containerIndicator
                else iconProgressCustom.disabledIndicator,
                cap = StrokeCap.Round,
                strokeWidth = size.height,
                start = Offset(x = 0f, y = center.y),
                end = Offset(x = size.width, y = center.y)
            )

            if (enabled) {
                val progress = (animateNumber / maxNumber) * size.width
                if (animateNumber != 0f)
                    drawLine(
                        color = if (numberTimes != maxNumber) iconProgressCustom.foregroundIndicator
                        else iconProgressCustom.foregroundIndicatorComplete,
                        cap = StrokeCap.Round,
                        strokeWidth = size.height,
                        start = Offset(x = 0f, y = center.y),
                        end = Offset(x = progress, y = center.y)
                    )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .clip(iconProgressCounter.shape.cornerRadius())
                .background(
                    if (enabled) iconProgressCounter.container
                    else iconProgressCounter.disabledIndicator
                )
                .padding(horizontal = 15.dp),
            text = if (enabled) "${numberTimes.toInt()}/${maxNumber.toInt()}" else "- / -",
            style = style,
            textAlign = TextAlign.Center,
            color = iconProgressCounter.content
        )
    }
}

@Immutable
class IconProgressCustom constructor(
    val iconSize: Dp = 50.dp,
    val iconPadding: Dp = 10.dp,
    val container: Color = Color.Gray,
    val content: Color = Color.LightGray.copy(alpha = 0.3f),
    val disabledIndicator: Color = Color(0xFF35898f),
    val containerIndicator: Color = Color(0xFF35898f),
    val foregroundIndicator: Color = Color(0xFF35898f),
    val foregroundIndicatorComplete: Color = Color(0xFF7DA88C)
)

@Immutable
class IconProgressCounter constructor(
    val shape: Int = 10,
    val disabledIndicator: Color = Color.Gray,
    val container: Color = Color(0xFF35898f),
    val content: Color = cosmicLatte
)

@Preview(showBackground = true)
@Composable
fun IconProgressbarPreview(
    primary: Color = Color(0xFFA9D2B5),
    secondary: Color = Color(0xFF79BA98),
    milky: Color = Color(0xFFF9F8DD)
) = Column(
    verticalArrangement = Arrangement.spacedBy(15.dp)
) {
    Text(text = "IconProgress example in LazyVerticalGrid")
    LazyVerticalGrid(
        modifier = Modifier.height(310.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(3)
    ) {
        repeat(5) { number ->
            item {
                IconProgress(
                    icon = R.drawable.ic_btn_share.toImageVector(),
                    enabled = true,
                    number = number.toFloat() + 1f,
                    maxNumber = 5f,
                    iconProgressCustom = IconProgressCustom(
                        container = primary,
                        content = secondary,
                        containerIndicator = secondary,
                        foregroundIndicator = milky
                    ),
                    iconProgressCounter = IconProgressCounter(
                        container = secondary
                    )
                )
            }
        }
    }
    Text(text = "LinearProgressbar with ImageVector")
    Text(text = "Disable")
    IconProgress(
        modifier = Modifier.padding(10.dp),
        icon = R.drawable.ic_btn_share.toImageVector(),
        enabled = false,
        number = 0f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "Enabled")
    IconProgress(
        icon = R.drawable.ic_btn_share.toImageVector(),
        number = 0f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "When the number is less than the maximum")
    IconProgress(
        icon = R.drawable.ic_btn_share.toImageVector(),
        number = 400f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "When the number is equal to the maximum")
    IconProgress(
        icon = R.drawable.ic_btn_share.toImageVector(),
        number = 1000f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "When the number is greater than the maximum")
    IconProgress(
        icon = R.drawable.ic_btn_share.toImageVector(),
        number = 2000f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Divider()

    Text(text = "LinearProgressbar with Painter")

    Text(text = "Disable")
    IconProgress(
        icon = R.drawable.ic_deco_jeluchu.toPainter(),
        enabled = false,
        number = 0f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "Enable")
    IconProgress(
        icon = R.drawable.ic_deco_jeluchu.toPainter(),
        number = 0f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "When the number is less than the maximum")
    IconProgress(
        icon = R.drawable.ic_deco_jeluchu.toPainter(),
        number = 400f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "When the number is equal to the maximum")
    IconProgress(
        icon = R.drawable.ic_deco_jeluchu.toPainter(),
        number = 1000f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )

    Text(text = "When the number is greater than the maximum")
    IconProgress(
        icon = R.drawable.ic_deco_jeluchu.toPainter(),
        number = 2000f,
        maxNumber = 1000f,
        iconProgressCustom = IconProgressCustom(
            container = primary,
            content = secondary,
            containerIndicator = secondary,
            foregroundIndicator = milky
        ),
        iconProgressCounter = IconProgressCounter(
            container = secondary
        )
    )
}