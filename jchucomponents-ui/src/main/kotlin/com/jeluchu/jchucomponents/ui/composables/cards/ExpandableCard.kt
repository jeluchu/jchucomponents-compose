/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.cards

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.compose.toPainter
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.extensions.modifier.noRippleClickable
import com.jeluchu.jchucomponents.ui.foundation.icon.IconLink
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf

const val ExpandAnimation = 300
const val CollapseAnimation = 300
const val FadeInAnimation = 300
const val FadeOutAnimation = 300

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    tint: Color = Color.White,
    contentColor: Color = Color.White,
    containerColor: Color = Color.DarkGray,
    style: TextStyle = LocalTextStyle.current,
    showArrow: Boolean = true,
    content: @Composable () -> Unit,
    onCardArrowClick: () -> Unit,
    expanded: Boolean
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(targetState = transitionState, label = "transition")
    val background by transition.animateColor({
        tween(durationMillis = ExpandAnimation)
    }, label = "bgColorTransition") {
        if (expanded) containerColor else containerColor
    }

    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = ExpandAnimation,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        15.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = ExpandAnimation)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cardRoundedCorners))
            .background(background)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(if (showArrow) 0.85f else 1f)
                    .noRippleClickable { if (!showArrow) onCardArrowClick() }
            ) {
                Text(
                    text = title,
                    color = contentColor,
                    textAlign = TextAlign.Start,
                    style = style
                )
            }
            if (showArrow)
                IconLink(
                    tint = tint,
                    onClick = onCardArrowClick,
                    contentDescription = "Expandable Arrow",
                    painter = R.drawable.ic_up_arrow.toPainter(),
                    modifier = Modifier.rotate(arrowRotationDegree)
                )
        }

        AnimatedVisibility(
            modifier = Modifier.animateContentSize(),
            visible = expanded,
        ) { content() }
    }
}

@Preview
@Composable
fun ExpandableCardPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        val isExpanded = rememberMutableStateOf(value = false)

        ExpandableCard(
            title = "Elemento",
            content = {
                Text("Expanded!")
            },
            expanded = false,
            onCardArrowClick = {}
        )

        ExpandableCard(
            title = "Elemento",
            content = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    text = "Expanded!",
                    color = Color.Yellow
                )
            },
            expanded = isExpanded.value,
            onCardArrowClick = { isExpanded.value = !isExpanded.value }
        )
    }
}