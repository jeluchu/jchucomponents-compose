package com.jeluchu.composer.core.commons.models

import com.jeluchu.composer.core.utils.DestinationsIds
import com.jeluchu.composer.core.utils.Names

data class MenuOptions(
    val id: String,
    val name: String
) {
    companion object {
        val ui = listOf(
            MenuOptions(
                id = DestinationsIds.buttons,
                name = Names.buttons
            ),
            MenuOptions(
                id = DestinationsIds.progress,
                name = Names.progress
            ),
            MenuOptions(
                id = DestinationsIds.lazyGrids,
                name = Names.lazyGrids
            ),
            MenuOptions(
                id = DestinationsIds.dividers,
                name = Names.dividers
            ),
            MenuOptions(
                id = DestinationsIds.toolbars,
                name = Names.toolbars
            ),
        )

        val buttons = listOf(
            MenuOptions(
                id = DestinationsIds.floatingButton,
                name = Names.floatingButtons
            ),
        )

        val progress = listOf(
            MenuOptions(
                id = DestinationsIds.linearProgress,
                name = Names.linearProgress
            ),
            MenuOptions(
                id = DestinationsIds.iconProgress,
                name = Names.iconProgress
            )
        )

        val lazyGrids = listOf(
            MenuOptions(
                id = DestinationsIds.lazyStaticGrids,
                name = Names.lazyStaticGrids
            ),
        )

        val toolbars = listOf(
            MenuOptions(
                id = DestinationsIds.simpleToolbars,
                name = Names.toolbars
            ),
            MenuOptions(
                id = DestinationsIds.centerToolbars,
                name = Names.centerToolbars
            ),
            MenuOptions(
                id = DestinationsIds.largeToolbars,
                name = Names.largeToolbars
            ),
        )
    }
}