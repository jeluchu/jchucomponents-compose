package com.jeluchu.jchucomponents.ui.animations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.jeluchu.jchucomponents.ui.composables.cards.CollapseAnimation
import com.jeluchu.jchucomponents.ui.composables.cards.ExpandAnimation
import com.jeluchu.jchucomponents.ui.composables.cards.FadeInAnimation
import com.jeluchu.jchucomponents.ui.composables.cards.FadeOutAnimation

val enterFadeIn: @Composable () -> EnterTransition = {
    remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = FadeInAnimation,
                easing = FastOutLinearInEasing
            )
        )
    }
}

val enterExpand: @Composable () -> EnterTransition = {
    remember {
        expandVertically(animationSpec = tween(ExpandAnimation))
    }
}

val exitFadeOut: @Composable () -> ExitTransition = {
    remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = FadeOutAnimation,
                easing = LinearOutSlowInEasing
            )
        )
    }
}

val exitCollapse: @Composable () -> ExitTransition = {
    remember {
        shrinkVertically(animationSpec = tween(CollapseAnimation))
    }
}