package com.jeluchu.composer.core.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jeluchu.composer.core.ui.theme.milky
import com.jeluchu.composer.core.ui.theme.secondary
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ktx.compose.toPainter
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.column.ScrollableColumn
import com.jeluchu.jchucomponents.ui.composables.toolbars.CenterToolbarColors
import com.jeluchu.jchucomponents.ui.composables.toolbars.Toolbar
import com.jeluchu.jchucomponents.ui.foundation.icon.IconLink
import com.jeluchu.jchucomponents.ui.foundation.lists.ListColumn

@Composable
fun ScaffoldStructure(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) = Scaffold(
    topBar = {
        Toolbar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            navigationIcon = navigationIcon,
            colors = CenterToolbarColors(
                containerColor = secondary,
                contentColor = milky
            )
        )
    },
    containerColor = secondary
) { contentPadding ->
    ScrollableColumn(
        modifier = Modifier.padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) { content() }
}

@Composable
fun ScaffoldStructure(
    title: String,
    onNavIconClick: () -> Unit = {},
    @DrawableRes navIcon: Int = R.drawable.ic_arrow_left,
    actionsIcons: @Composable (RowScope.() -> Unit) = {},
    colors: CenterToolbarColors = CenterToolbarColors(),
    content: @Composable () -> Unit,
) = Scaffold(
    topBar = {
        Toolbar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            actions = actionsIcons,
            navigationIcon = {
                IconLink(
                    imageVector = navIcon.toImageVector(),
                    contentDescription = "ToolbarBack"
                ) { onNavIconClick() }
            },
            colors = colors
        )
    },
    containerColor = colors.containerColor
) { contentPadding ->
    ScrollableColumn(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) { content() }
}

@Composable
fun ScaffoldListStructure(
    title: String,
    onNavIconClick: () -> Unit = {},
    @DrawableRes navIcon: Int = R.drawable.ic_arrow_left,
    actionsIcons: @Composable (RowScope.() -> Unit) = {},
    colors: CenterToolbarColors = CenterToolbarColors(),
    content: LazyListScope.() -> Unit
) = Scaffold(
    topBar = {
        Toolbar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            actions = actionsIcons,
            navigationIcon = {
                IconLink(
                    painter = navIcon.toPainter(),
                    contentDescription = "ToolbarBack"
                ) { onNavIconClick() }
            },
            colors = colors
        )
    },
    containerColor = colors.containerColor
) { contentPadding ->
    ListColumn(
        modifier = Modifier.padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) { content() }
}