package com.jeluchu.jchucomponents.ui.composables.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReadingIndicator(
    lastPage: Float,
    totalPage: Float,
    modifier: Modifier = Modifier
) {
    val color = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray

    var width by remember {
        mutableStateOf(0f)
    }

    val anim by animateFloatAsState(
        targetValue = width * (lastPage / totalPage),
        tween(durationMillis = 2000)
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().padding(20.dp).height(20.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .drawBehind {
                    width = size.width
                    drawLine(
                        color = color,
                        start = Offset.Zero,
                        end = Offset(size.width, 0f),
                        cap = StrokeCap.Round,
                        strokeWidth = 20f
                    )
                }
                .drawBehind {
                    drawLine(
                        color = Color.DarkGray,
                        start = Offset.Zero,
                        end = Offset(anim, 0f),
                        cap = StrokeCap.Round,
                        strokeWidth = 20f
                    )
                }
        )
    }
}

@Preview
@Composable
fun ReadingIndicatorPreview() {
    ReadingIndicator(
        lastPage = 2f,
        totalPage = 10f,
    )
}