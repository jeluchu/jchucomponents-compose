package com.jeluchu.composer.features.progress.view

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.jeluchu.composer.core.commons.models.MenuOptions
import com.jeluchu.composer.core.ui.composables.ScaffoldStructure
import com.jeluchu.composer.core.ui.composables.SimpleButton
import com.jeluchu.composer.core.ui.theme.milky
import com.jeluchu.composer.core.ui.theme.primary
import com.jeluchu.composer.core.ui.theme.secondary
import com.jeluchu.composer.core.utils.DestinationsIds
import com.jeluchu.composer.core.utils.Names
import com.jeluchu.jchucomponents.ui.accompanist.systemui.SystemStatusBarColors
import com.jeluchu.jchucomponents.ui.composables.toolbars.CenterToolbarColors
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun ProgressView(onItemClick: (String) -> Unit) {
    SystemStatusBarColors(
        systemBarsColor = secondary,
        statusBarColor = secondary
    )

    Progress(onItemClick)
}

@Composable
private fun Progress(
    onItemClick: (String) -> Unit
) = ScaffoldStructure(
    title = Names.buttons,
    colors = CenterToolbarColors(
        containerColor = secondary,
        contentColor = milky
    ),
    onNavIconClick = { onItemClick(DestinationsIds.back) }
) {
    MenuOptions.progress.forEach { option ->
        SimpleButton(
            modifier = Modifier
                .clip(10.cornerRadius())
                .background(primary.copy(.7f)),
            label = option.name,
            color = Color.DarkGray
        ) { onItemClick(option.id) }
    }
}