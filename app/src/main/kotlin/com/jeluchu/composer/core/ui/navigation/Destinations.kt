package com.jeluchu.composer.core.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.jeluchu.jchucomponents.ktx.navigation.lifecycleIsResumed

class Destinations(private val navController: NavHostController) {

    private fun String.navigate() = navController.navigate(this)

    val goToDashboard: () -> Unit = { Feature.DASHBOARD.route.navigate() }
    val goToButtons: () -> Unit = { Feature.BUTTONS.route.navigate() }
    val goToProgress: () -> Unit = { Feature.PROGRESS.route.navigate() }
    val goToLazyGrids: () -> Unit = { Feature.LAZY_GRIDS.route.navigate() }
    val goToFloatingButtons: () -> Unit = { Feature.FLOATING_BUTTONS.route.navigate() }
    val goToLinearProgress: () -> Unit = { Feature.LINEAR_PROGRESS.route.navigate() }
    val goToIconProgress: () -> Unit = { Feature.ICON_PROGRESS.route.navigate() }
    val goToLazyStaticGrid: () -> Unit = { Feature.LAZY_STATIC_GRIDS.route.navigate() }
    val goToDividers: () -> Unit = { Feature.DIVIDERS.route.navigate() }
    val goToToolbars: () -> Unit = { Feature.TOOLBARS.route.navigate() }
    val goToSimpleToolbars: () -> Unit = { Feature.SIMPLE_TOOLBARS.route.navigate() }
    val goToCenterToolbars: () -> Unit = { Feature.CENTER_TOOBARS.route.navigate() }
    val goToLargeToolbars: () -> Unit = { Feature.LARGE_TOOBARS.route.navigate() }

    val goBack: (from: NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) navController.popBackStack()
    }
}