package com.jeluchu.composer.features.progress.view

import androidx.compose.runtime.Composable
import com.jeluchu.composer.core.ui.composables.ScaffoldStructure
import com.jeluchu.composer.core.ui.theme.milky
import com.jeluchu.composer.core.ui.theme.secondary
import com.jeluchu.composer.core.utils.DestinationsIds
import com.jeluchu.composer.core.utils.Names
import com.jeluchu.jchucomponents.ui.accompanist.systemui.SystemStatusBarColors
import com.jeluchu.jchucomponents.ui.composables.progress.IconProgressbarPreview
import com.jeluchu.jchucomponents.ui.composables.toolbars.CenterToolbarColors

@Composable
fun IconProgressbarView(onItemClick: (String) -> Unit) {
    SystemStatusBarColors(
        systemBarsColor = secondary,
        statusBarColor = secondary
    )

    IconProgressbar(onItemClick)
}

@Composable
private fun IconProgressbar(
    onItemClick: (String) -> Unit
) = ScaffoldStructure(
    title = Names.iconProgress,
    colors = CenterToolbarColors(
        containerColor = secondary,
        contentColor = milky
    ),
    onNavIconClick = { onItemClick(DestinationsIds.back) }
) { IconProgressbarPreview() }