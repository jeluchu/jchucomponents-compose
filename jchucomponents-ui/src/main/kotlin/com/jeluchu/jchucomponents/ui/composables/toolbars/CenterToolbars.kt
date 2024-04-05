package com.jeluchu.jchucomponents.ui.composables.toolbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.column.ScrollableColumn
import com.jeluchu.jchucomponents.ui.foundation.icon.IconLink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterToolbar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: CenterToolbarColors = CenterToolbarColors()
) = CenterAlignedTopAppBar(
    title = title,
    actions = actions,
    modifier = modifier,
    navigationIcon = navigationIcon,
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = colors.containerColor,
        titleContentColor = colors.contentColor,
        actionIconContentColor = colors.contentColor,
        navigationIconContentColor = colors.contentColor
    )
)

@Immutable
data class CenterToolbarColors(
    val containerColor: Color = Color.White,
    val contentColor: Color = Color.DarkGray
)

@Preview
@Composable
fun CenterToolbarActionsPreview() = ScrollableColumn(
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    CenterToolbar(
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

    CenterToolbar(
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

    CenterToolbar(
        modifier = Modifier,
        title = { Text(text = "Villagers") },
        navigationIcon = {
            IconLink(
                imageVector = R.drawable.ic_arrow_left.toImageVector(),
                contentDescription = "ToolbarTest",
                tint = Color.DarkGray,
                onClick = {}
            )
        },
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