/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.composables.images.NetworkImage
import com.jeluchu.jchucomponents.ui.foundation.text.MarqueeText

/**
 *
 * Author: @Jeluchu
 *
 * Component displaying
 * information on a custom-designed card
 *
 * @param modifier modifier that will be used to change the color, size...
 * @param title text to be displayed on the card
 * @param image link of the image you want to be displayed on Card
 * @param iconDebut  link of the icon you want to be displayed on Card
 * @param isDebut status to display a banner with information at the top
 * @param bgDebut background color of the flag state with information
 * @param navigateToScreen action to be performed after pressing
 *
 */

@Composable
fun DebutCard(
    modifier: Modifier = Modifier,
    title: String = String.empty(),
    image: String,
    titleColor: Color = Color.Black,
    debutColor: Color = Color.Black,
    gradientEdgeColor: Color = Color.Transparent,
    style: TextStyle = LocalTextStyle.current,
    iconDebut: String,
    nameOfDebut: String = String.empty(),
    isDebut: Boolean = false,
    bgDebut: Color = Color.Black,
    debubtAlignment: Alignment = Alignment.TopStart,
    debutShape: RoundedCornerShape = RoundedCornerShape(bottomEnd = 20.dp),
    navigateToScreen: () -> Unit = {}
) = Column(
    modifier = modifier
        .width(130.dp)
        .height(190.dp)
        .padding(4.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(Color.Transparent)
        .clickable(onClick = navigateToScreen)
) {

    Box {

        NetworkImage(url = image)

        if (isDebut) {

            Box(
                modifier = Modifier
                    .clip(debutShape)
                    .background(bgDebut)
                    .align(debubtAlignment),
            ) {

                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    NetworkImage(
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(35.dp)
                            .padding(
                                start = 8.dp,
                                end = if (nameOfDebut.isNotEmpty()) 0.dp else 5.dp
                            ),
                        url = iconDebut
                    )

                    if (nameOfDebut.isNotEmpty())
                        Text(
                            text = nameOfDebut,
                            modifier = Modifier.padding(8.dp, 6.dp, 12.dp, 6.dp),
                            color = debutColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.overline,
                            textAlign = TextAlign.Start
                        )

                }

            }

        }

    }

    if (title.isNotEmpty())
        MarqueeText(
            text = title,
            fontSize = 12.sp,
            color = titleColor,
            modifier = Modifier
                .width(130.dp)
                .padding(7.dp),
            style = style,
            fontWeight = FontWeight.Bold
        )

}