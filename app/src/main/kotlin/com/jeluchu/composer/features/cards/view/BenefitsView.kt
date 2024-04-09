package com.jeluchu.composer.features.cards.view

import androidx.compose.runtime.Composable
import com.jeluchu.composer.core.ui.composables.ScaffoldStructure
import com.jeluchu.composer.core.ui.theme.milky
import com.jeluchu.composer.core.ui.theme.secondary
import com.jeluchu.composer.core.utils.DestinationsIds
import com.jeluchu.composer.core.utils.Names
import com.jeluchu.jchucomponents.ui.accompanist.systemui.SystemStatusBarColors
import com.jeluchu.jchucomponents.ui.composables.cards.BenefitsCardPreview
import com.jeluchu.jchucomponents.ui.composables.toolbars.CenterToolbarColors

@Composable
fun BenefitsView(onItemClick: (String) -> Unit) {
    SystemStatusBarColors(
        systemBarsColor = secondary,
        statusBarColor = secondary
    )

    Benefits(onItemClick)
}

@Composable
private fun Benefits(
    onItemClick: (String) -> Unit
) = ScaffoldStructure(
    title = Names.benefitCards,
    colors = CenterToolbarColors(
        containerColor = secondary,
        contentColor = milky
    ),
    onNavIconClick = { onItemClick(DestinationsIds.back) }
) { BenefitsCardPreview() }