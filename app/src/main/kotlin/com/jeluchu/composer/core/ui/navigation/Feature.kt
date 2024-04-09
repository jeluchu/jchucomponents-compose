package com.jeluchu.composer.core.ui.navigation

enum class Feature(val route: String) {
    DASHBOARD("dashboard"),

    // Buttons
    BUTTONS("buttons"),
    FLOATING_BUTTONS("floating_buttons"),

    // Progress
    PROGRESS("progress"),
    LINEAR_PROGRESS("linear_progress"),
    ICON_PROGRESS("icon_progress"),

    // Lists
    LAZY_GRIDS("lazy_grids"),
    LAZY_STATIC_GRIDS("lazy_static_grids"),

    // Dividers
    DIVIDERS("dividers"),

    // Toolbars
    TOOLBARS("toolbars"),
    SIMPLE_TOOLBARS("simple_toolbars"),
    CENTER_TOOBARS("center_toolbars"),
    LARGE_TOOBARS("large_toolbars"),

    // Cards
    CARDS("cards"),
    BENEFIT_CARDS("benefitCards"),
}

val Feature.baseRoute: String
    get() = NavItem.ContentScreen(this).route

val Feature.nav: NavItem.ContentScreen
    get() = NavItem.ContentScreen(this)
