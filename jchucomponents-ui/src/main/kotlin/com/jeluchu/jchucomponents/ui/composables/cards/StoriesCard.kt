/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.numbers.isNotEmpty
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.composables.images.NetworkImage
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius
import com.jeluchu.jchucomponents.ui.extensions.modifier.noRippleClickable

/**
 *
 * Author: @Jeluchu
 *
 * This component displays the same design as Facebook stories
 *
 * @param modifier modifier that will be used to added animations (for example)
 * @param title text to be displayed on the chip
 * @param textColor color of title
 * @param circleImage link of the image you want to be displayed as an icon on top storie
 * @param iconMainUrl link of the image you want to be displayed as an image
 * @param iconMainResource drawable id of the resource you want to be displayed as an image
 * @param navigateToScreen action to be performed after pressing
 *
 */

@Composable
fun StoryCard(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = Color.Black,
    circleImage: Int,
    iconMainUrl: String = String.empty(),
    iconMainResource: Int = 0,
    navigateToScreen: () -> Unit
) = Card(
    shape = 12.cornerRadius(),
    modifier = modifier
        .width(130.dp)
        .height(190.dp)
        .padding(4.dp)
        .clip(12.cornerRadius())
        .noRippleClickable { navigateToScreen() },
    colors = CardDefaults.cardColors().copy(
        containerColor = Color.DarkGray
    )
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            iconMainUrl.isNotEmpty() -> {
                NetworkImage(
                    url = iconMainUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            iconMainResource.isNotEmpty() -> {
                Icon(
                    modifier = Modifier
                        .size(23.dp)
                        .padding(start = 7.dp),
                    painter = painterResource(id = iconMainResource),
                    contentDescription = null
                )
            }
        }

        Text(
            text = title,
            fontSize = 13.sp,
            color = textColor,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )

        Card(
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
                .width(40.dp)
                .height(40.dp)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = CircleShape
                )
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(circleImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    }
}

