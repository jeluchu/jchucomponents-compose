package com.jeluchu.composer.core.ui.navigation

enum class Feature(val route: String) {
    DASHBOARD("dashboard"),
    BUTTONS("buttons"),
    FLOATING_BUTTONS("floating_buttons"),
    PROGRESS("progress"),
    LINEAR_PROGRESS("linear_progress"),
    ICON_PROGRESS("icon_progress"),
    LAZY_GRIDS("lazy_grids"),
    LAZY_STATIC_GRIDS("lazy_static_grids"),
    DIVIDERS("dividers"),
    TOOLBARS("toolbars"),
    SIMPLE_TOOLBARS("simple_toolbars"),
    CENTER_TOOBARS("center_toolbars"),
    LARGE_TOOBARS("large_toolbars"),
}

val Feature.baseRoute: String
    get() = NavItem.ContentScreen(this).route

val Feature.nav: NavItem.ContentScreen
    get() = NavItem.ContentScreen(this)
