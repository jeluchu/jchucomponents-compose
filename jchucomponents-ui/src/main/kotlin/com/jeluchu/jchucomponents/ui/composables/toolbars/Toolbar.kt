/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.toolbars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.column.ScrollableColumn
import com.jeluchu.jchucomponents.ui.extensions.modifier.noRippleClickable
import com.jeluchu.jchucomponents.ui.foundation.icon.IconLink

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = String.empty(),
    shapeActions: Shape = RoundedCornerShape(
        topStart = 0.dp,
        bottomStart = 0.dp,
        topEnd = 6.dp,
        bottomEnd = 6.dp
    ),
    style: TextStyle = LocalTextStyle.current,
    topBarSettings: TopBarSettings = TopBarSettings(),
    navigateToCustomAction: () -> Unit,
    navigateToBackScreen: () -> Unit
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .height(52.dp)
        .background(topBarSettings.backgroundColor)
) {
    Row(
        modifier = Modifier
            .clip(shapeActions)
            .background(topBarSettings.actionsBackgroundColor)
            .align(Alignment.CenterStart)
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
                .clip(CircleShape)
                .clickable(onClick = navigateToBackScreen),
            tint = topBarSettings.tintActionsColor,
            imageVector = ImageVector.vectorResource(id = topBarSettings.navIcon),
            contentDescription = null
        )
        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
                .noRippleClickable { navigateToCustomAction() },
            tint = topBarSettings.tintActionsColor,
            imageVector = ImageVector.vectorResource(id = topBarSettings.actionIcon),
            contentDescription = null
        )
    }

    Text(
        text = title,
        fontSize = 20.sp,
        color = topBarSettings.contentColor,
        style = style,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterEnd)
            .padding(end = 10.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = String.empty(),
    topBarSettings: TopBarSettings = TopBarSettings(),
    style: TextStyle = LocalTextStyle.current,
    navigateToBackScreen: () -> Unit
) = TopAppBar(
    modifier = modifier,
    title = {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
            text = title,
            fontSize = 20.sp,
            color = topBarSettings.contentColor,
            style = style,
            textAlign = TextAlign.End,
            maxLines = 1
        )
    },
    colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = topBarSettings.backgroundColor
    ),
    navigationIcon = {
        IconButton(onClick = navigateToBackScreen) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp),
                tint = topBarSettings.contentColor,
                imageVector = ImageVector.vectorResource(id = topBarSettings.navIcon),
                contentDescription = null
            )
        }
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: CenterToolbarColors = CenterToolbarColors()
) = TopAppBar(
    title = title,
    actions = actions,
    modifier = modifier,
    navigationIcon = navigationIcon,
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = colors.containerColor,
        titleContentColor = colors.contentColor,
        actionIconContentColor = colors.contentColor,
        navigationIconContentColor = colors.contentColor
    )
)

@Immutable
class TopBarSettings constructor(
    @DrawableRes val navIcon: Int = R.drawable.ic_arrow_left,
    @DrawableRes val actionIcon: Int = R.drawable.ic_btn_qrcode,
    val contentColor: Color = Color.DarkGray,
    val actionsBackgroundColor: Color = Color.DarkGray,
    val tintActionsColor: Color = Color.White,
    val backgroundColor: Color = Color.White
)

@Preview
@Composable
fun ToolbarActionsPreview() {
    ScrollableColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Toolbar(
            modifier = Modifier,
            title = "Villagers",
            topBarSettings = TopBarSettings(
                actionIcon = R.drawable.ic_btn_qrcode
            ),
            navigateToCustomAction = { },
            navigateToBackScreen = { }
        )

        Toolbar(
            modifier = Modifier,
            title = "Villagers",
            navigateToBackScreen = { }
        )

        Toolbar(
            modifier = Modifier,
            title = { Text(text = "Villagers") },
            navigationIcon = {
                IconLink(
                    imageVector = R.drawable.ic_btn_qrcode.toImageVector(),
                    contentDescription = "ToolbarTest",
                    tint = Color.DarkGray,
                    onClick = {}
                )
            }
        )

        Toolbar(
            modifier = Modifier,
            title = { Text(text = "Villagers") },
            actions = {
                IconLink(
                    imageVector = R.drawable.ic_btn_qrcode.toImageVector(),
                    contentDescription = "ToolbarTest",
                    tint = Color.DarkGray,
                    onClick = {}
                )
            }
        )
    }
}