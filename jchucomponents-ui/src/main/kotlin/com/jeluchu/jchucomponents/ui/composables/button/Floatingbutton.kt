/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.themes.artichoke
import com.jeluchu.jchucomponents.ui.themes.cosmicLatte

@Composable
fun FloatingButton(
    enabled: Boolean = true,
    isVisible: Boolean = true,
    exit: ExitTransition = scaleOut(),
    enter: EnterTransition = scaleIn(),
    size: FloatingButtonSize = FloatingButtonSize.Medium,
    floatButton: FloatingButtonSettings = FloatingButtonSettings(),
    onClick: () -> Unit = {}
) = AnimatedVisibility(
    visible = isVisible,
    enter = enter,
    exit = exit
) {
    FloatingActionButton(
        modifier = Modifier.size(size.buttonSize),
        containerColor = if (enabled) floatButton.background else floatButton.disabledBackground,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(size.shape),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(size.iconSize),
            imageVector = ImageVector.vectorResource(id = floatButton.icon),
            contentDescription = String.empty(),
            tint = if (enabled) floatButton.tint else floatButton.disabledTint
        )
    }
}

@Immutable
class FloatingButtonSettings constructor(
    @DrawableRes val icon: Int = R.drawable.ic_btn_share,
    val tint: Color = cosmicLatte,
    val background: Color = artichoke,
    val disabledBackground: Color = Color.LightGray,
    val disabledTint: Color = Color.Gray,
)

@Immutable
sealed class FloatingButtonSize(val buttonSize: Dp, val iconSize: Dp, val shape: Dp) {
    @Immutable
    data object Large : FloatingButtonSize(buttonSize = 64.dp, iconSize = 30.dp, shape = 15.dp)

    @Immutable
    data object Medium : FloatingButtonSize(buttonSize = 48.dp, iconSize = 24.dp, shape = 12.dp)

    @Immutable
    data object Small : FloatingButtonSize(buttonSize = 32.dp, iconSize = 16.dp, shape = 8.dp)

    @Immutable
    data object Border : FloatingButtonSize(buttonSize = 32.dp, iconSize = 24.dp, shape = 8.dp)

    @Immutable
    class Custom(buttonSize: Dp, iconSize: Dp, shape: Dp) :
        FloatingButtonSize(buttonSize, iconSize, shape)
}

@Preview
@Composable
fun FloatingButtonPreview(
    primary: Color = Color(0xFFA9D2B5),
    secondary: Color = Color(0xFF79BA98),
    milky: Color = Color(0xFFF9F8DD)
) = Column(
    verticalArrangement = Arrangement.spacedBy(15.dp)
) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(1f)) {}
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(text = "Large", fontSize = 16.sp)
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(text = "Medium", fontSize = 16.sp)
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(text = "Small", fontSize = 16.sp)
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(text = "Border", fontSize = 16.sp)
        }
    }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Enabled", fontSize = 16.sp, modifier = Modifier.weight(1f))
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            FloatingButton(
                size = FloatingButtonSize.Large,
                floatButton = FloatingButtonSettings(
                    tint = Color.DarkGray,
                    background = primary
                )
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp), contentAlignment = Alignment.Center
        ) {
            FloatingButton(
                size = FloatingButtonSize.Medium,
                floatButton = FloatingButtonSettings(
                    tint = Color.DarkGray,
                    background = primary
                )
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp), contentAlignment = Alignment.Center
        ) {
            FloatingButton(
                size = FloatingButtonSize.Small,
                floatButton = FloatingButtonSettings(
                    tint = Color.DarkGray,
                    background = primary
                )
            )
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            FloatingButton(
                size = FloatingButtonSize.Border,
                floatButton = FloatingButtonSettings(
                    tint = Color.DarkGray,
                    background = primary
                )
            )
        }
    }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Disable", fontSize = 16.sp, modifier = Modifier.weight(1f))
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            FloatingButton(
                enabled = false,
                size = FloatingButtonSize.Large,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp), contentAlignment = Alignment.Center
        ) {
            FloatingButton(
                enabled = false,
                size = FloatingButtonSize.Medium
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp), contentAlignment = Alignment.Center
        ) {
            FloatingButton(
                enabled = false,
                size = FloatingButtonSize.Small
            )
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            FloatingButton(
                enabled = false,
                size = FloatingButtonSize.Border
            )
        }
    }
}