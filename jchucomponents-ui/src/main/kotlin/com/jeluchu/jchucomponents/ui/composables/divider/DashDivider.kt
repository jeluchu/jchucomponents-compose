package com.jeluchu.jchucomponents.ui.composables.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf

@Composable
inline fun DashedDivider(
    color: Color,
    dashWidth: Float,
    dashGap: Float,
    modifier: Modifier = Modifier,
    height: Dp = 1.dp,
    crossinline offsetStart: DrawScope.() -> Offset = { Offset.Zero },
    crossinline offsetEnd: DrawScope.() -> Offset = { Offset(size.width, 0f) },
    phase: Float = 0f,
) = Canvas(
    modifier = modifier
        .fillMaxWidth()
        .height(height)
) {
    drawLine(
        color = color,
        start = offsetStart(),
        end = offsetEnd(),
        strokeWidth = size.height,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), phase)
    )
}

@Preview(showBackground = true)
@Composable
fun DashedDividerStaticPreview() {
    val samples = remember {
        listOf(
            5f to 5f,
            5f to 10f,
            5f to 25f,
            10f to 5f,
            10f to 10f,
            10f to 25f,
            25f to 5f,
            25f to 10f,
            25f to 25f,
        )
    }

    Column {
        samples.forEach { (dashWidth, dashGap) ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "dash width = $dashWidth\ndash gap = $dashGap",
                )

                DashedDivider(
                    dashWidth = dashWidth,
                    dashGap = dashGap,
                    color = Color.DarkGray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashedDividerInteractivePreview(
    primary: Color = Color(0xFFA9D2B5),
    secondary: Color = Color(0xFF79BA98),
    milky: Color = Color(0xFFF9F8DD)
) {
    var dashWidth by rememberMutableStateOf(5f)
    var dashGap by rememberMutableStateOf(5f)
    var height by rememberMutableStateOf(1.dp)
    var phase by rememberMutableStateOf(0f)

    @Composable
    fun ValueSlider(
        label: String,
        value: Float,
        onValueChanged: (Float) -> Unit,
        valueRange: ClosedFloatingPointRange<Float> = 1f..100f,
    ) {
        Column {
            Text(label)
            Slider(
                value = value,
                onValueChange = onValueChanged,
                valueRange = valueRange,
                colors = SliderDefaults.colors(
                    thumbColor = primary,
                    activeTrackColor = primary,
                    activeTickColor = secondary,
                    inactiveTrackColor = milky.copy(.6f)
                )
            )
        }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        DashedDivider(
            color = Color.DarkGray,
            dashWidth = dashWidth,
            dashGap = dashGap,
            height = height,
            phase = phase,
        )

        ValueSlider(
            label = "dash width = $dashWidth",
            value = dashWidth,
            onValueChanged = { dashWidth = it },
        )

        ValueSlider(
            label = "dash gap = $dashGap",
            value = dashGap,
            onValueChanged = { dashGap = it },
        )

        ValueSlider(
            label = "height = $height",
            value = height.value,
            onValueChanged = { height = it.dp },
            valueRange = 1f..25f,
        )

        ValueSlider(
            label = "phase = $phase",
            value = phase,
            onValueChanged = { phase = it },
            valueRange = 0f..100f,
        )
    }
}
