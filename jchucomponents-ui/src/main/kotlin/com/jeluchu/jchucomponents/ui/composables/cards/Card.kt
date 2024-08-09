package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun Card(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    shape: Shape = 10.cornerRadius(),
    colors: CardColors = CardColors(),
    elevation: CardElevation = CardElevation(),
    contentPadding: PaddingValues = PaddingValues(15.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = colors.containerColor(enabled),
        contentColor = colors.contentColor(enabled),
        tonalElevation = elevation.tonalElevation(enabled),
        shadowElevation = elevation.shadowElevation(enabled, interactionSource).value,
        border = border,
        interactionSource = interactionSource,
    ) {
        Column(
            content = content,
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Immutable
class CardColors(
    val contentColor: Color = Color.Black,
    val containerColor: Color = Color.White,
    val disabledContentColor: Color = Color.DarkGray,
    val disabledContainerColor: Color = Color.LightGray,
) {
    /**
     * Represents the container color for this card, depending on [enabled].
     *
     * @param enabled whether the card is enabled
     */
    @Stable
    internal fun containerColor(enabled: Boolean): Color =
        if (enabled) containerColor else disabledContainerColor

    /**
     * Represents the content color for this card, depending on [enabled].
     *
     * @param enabled whether the card is enabled
     */
    @Stable
    internal fun contentColor(enabled: Boolean) =
        if (enabled) contentColor else disabledContentColor
}

@Immutable
class CardElevation(
    val tonalElevation: Dp = 0.dp,
    val shadowElevation: Dp = 0.dp,
    val pressedElevation: Dp = 0.dp,
    val focusedElevation: Dp = 0.dp,
    val hoveredElevation: Dp = 0.dp,
    val draggedElevation: Dp = 0.dp,
    val disabledElevation: Dp = 0.dp,
) {
    /**
     * Represents the tonal elevation used in a card, depending on its [enabled].
     *
     * Tonal elevation is used to apply a color shift to the surface to give the it higher emphasis.
     * When surface's color is [ColorScheme.surface], a higher elevation will result in a darker
     * color in light theme and lighter color in dark theme.
     *
     * See [shadowElevation] which controls the elevation of the shadow drawn around the card.
     *
     * @param enabled whether the card is enabled
     */
    internal fun tonalElevation(enabled: Boolean): Dp =
        if (enabled) tonalElevation else disabledElevation

    /**
     * Represents the shadow elevation used in a card, depending on its [enabled] state and
     * [interactionSource].
     *
     * Shadow elevation is used to apply a shadow around the card to give it higher emphasis.
     *
     * See [tonalElevation] which controls the elevation with a color shift to the surface.
     *
     * @param enabled whether the card is enabled
     * @param interactionSource the [InteractionSource] for this card
     */
    @Composable
    internal fun shadowElevation(
        enabled: Boolean,
        interactionSource: InteractionSource?
    ): State<Dp> {
        if (interactionSource == null) {
            return remember { mutableStateOf(shadowElevation) }
        }
        return animateElevation(enabled = enabled, interactionSource = interactionSource)
    }

    @Composable
    private fun animateElevation(
        enabled: Boolean,
        interactionSource: InteractionSource
    ): State<Dp> {
        val interactions = remember { mutableStateListOf<Interaction>() }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is HoverInteraction.Enter -> {
                        interactions.add(interaction)
                    }

                    is HoverInteraction.Exit -> {
                        interactions.remove(interaction.enter)
                    }

                    is FocusInteraction.Focus -> {
                        interactions.add(interaction)
                    }

                    is FocusInteraction.Unfocus -> {
                        interactions.remove(interaction.focus)
                    }

                    is PressInteraction.Press -> {
                        interactions.add(interaction)
                    }

                    is PressInteraction.Release -> {
                        interactions.remove(interaction.press)
                    }

                    is PressInteraction.Cancel -> {
                        interactions.remove(interaction.press)
                    }

                    is DragInteraction.Start -> {
                        interactions.add(interaction)
                    }

                    is DragInteraction.Stop -> {
                        interactions.remove(interaction.start)
                    }

                    is DragInteraction.Cancel -> {
                        interactions.remove(interaction.start)
                    }
                }
            }
        }

        val interaction = interactions.lastOrNull()

        val target =
            if (!enabled) {
                disabledElevation
            } else {
                when (interaction) {
                    is PressInteraction.Press -> pressedElevation
                    is HoverInteraction.Enter -> hoveredElevation
                    is FocusInteraction.Focus -> focusedElevation
                    is DragInteraction.Start -> draggedElevation
                    else -> shadowElevation
                }
            }

        val animatable = remember { Animatable(target, Dp.VectorConverter) }

        LaunchedEffect(target) {
            if (animatable.targetValue != target) {
                if (!enabled) {
                    // No transition when moving to a disabled state.
                    animatable.snapTo(target)
                } else {
                    val lastInteraction = when (animatable.targetValue) {
                        pressedElevation -> PressInteraction.Press(Offset.Zero)
                        hoveredElevation -> HoverInteraction.Enter()
                        focusedElevation -> FocusInteraction.Focus()
                        draggedElevation -> DragInteraction.Start()
                        else -> null
                    }
                    animatable.animateElevation(
                        from = lastInteraction,
                        to = interaction,
                        target = target
                    )
                }
            }
        }

        return animatable.asState()
    }
}

/**
 * Animates the [Dp] value of [this] between [from] and [to] [Interaction]s, to [target]. The
 * [AnimationSpec] used depends on the values for [from] and [to], see
 * [ElevationDefaults.incomingAnimationSpecForInteraction] and
 * [ElevationDefaults.outgoingAnimationSpecForInteraction] for more details.
 *
 * @param target the [Dp] target elevation for this component, corresponding to the elevation
 * desired for the [to] state.
 * @param from the previous [Interaction] that was used to calculate elevation. `null` if there
 * was no previous [Interaction], such as when the component is in its default state.
 * @param to the [Interaction] that this component is moving to, such as [PressInteraction.Press]
 * when this component is being pressed. `null` if this component is moving back to its default
 * state.
 */
internal suspend fun Animatable<Dp, *>.animateElevation(
    target: Dp,
    from: Interaction? = null,
    to: Interaction? = null
) {
    val spec = when {
        // Moving to a new state
        to != null -> ElevationDefaults.incomingAnimationSpecForInteraction(to)
        // Moving to default, from a previous state
        from != null -> ElevationDefaults.outgoingAnimationSpecForInteraction(from)
        // Loading the initial state, or moving back to the baseline state from a disabled /
        // unknown state, so just snap to the final value.
        else -> null
    }
    if (spec != null) animateTo(target, spec) else snapTo(target)
}

private object ElevationDefaults {
    /**
     * Returns the [AnimationSpec]s used when animating elevation to [interaction], either from a
     * previous [Interaction], or from the default state. If [interaction] is unknown, then
     * returns `null`.
     *
     * @param interaction the [Interaction] that is being animated to
     */
    fun incomingAnimationSpecForInteraction(interaction: Interaction): AnimationSpec<Dp>? {
        return when (interaction) {
            is PressInteraction.Press -> DefaultIncomingSpec
            is DragInteraction.Start -> DefaultIncomingSpec
            is HoverInteraction.Enter -> DefaultIncomingSpec
            is FocusInteraction.Focus -> DefaultIncomingSpec
            else -> null
        }
    }

    /**
     * Returns the [AnimationSpec]s used when animating elevation away from [interaction], to the
     * default state. If [interaction] is unknown, then returns `null`.
     *
     * @param interaction the [Interaction] that is being animated away from
     */
    fun outgoingAnimationSpecForInteraction(interaction: Interaction): AnimationSpec<Dp>? {
        return when (interaction) {
            is PressInteraction.Press -> DefaultOutgoingSpec
            is DragInteraction.Start -> DefaultOutgoingSpec
            is HoverInteraction.Enter -> HoveredOutgoingSpec
            is FocusInteraction.Focus -> DefaultOutgoingSpec
            else -> null
        }
    }
}

private val OutgoingSpecEasing: Easing = CubicBezierEasing(0.40f, 0.00f, 0.60f, 1.00f)

private val DefaultIncomingSpec = TweenSpec<Dp>(
    durationMillis = 120,
    easing = FastOutSlowInEasing
)

private val DefaultOutgoingSpec = TweenSpec<Dp>(
    durationMillis = 150,
    easing = OutgoingSpecEasing
)

private val HoveredOutgoingSpec = TweenSpec<Dp>(
    durationMillis = 120,
    easing = OutgoingSpecEasing
)
