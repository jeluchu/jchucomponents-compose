/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.images.NetworkImage

/**
 *
 * Author: @Jeluchu
 *
 * Component displaying
 * information on a custom-designed card
 *
 * @param modifier modifier that will be used to change the color, size...
 * @param text text to be displayed on the card
 * @param color color of text that appears on Card
 * @param url link of the image you want to be displayed on Card
 *
 */
@Composable
fun InfoCard(
    modifier: Modifier,
    text: String,
    url: String = String.empty(),
    color: Color = Color.Gray,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    NetworkImage(
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(34.dp),
        url = url
    )

    Text(
        text = text,
        modifier = Modifier.padding(
            start = 12.dp,
            top = 6.dp,
            bottom = 6.dp
        ),
        color = color,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.overline,
        textAlign = TextAlign.Start
    )
}

/**
 *
 * Author: @Jeluchu
 *
 * Component displaying
 * information on a custom-designed card
 *
 * @param modifier modifier that will be used to change the color, size...
 * @param text text to be displayed on the card
 * @param color color of text that appears on Card
 * @param icon drawable id of the resource you want to be displayed as an icon on Card
 * @param tint color of the icon displayed on Card
 *
 */
@Composable
fun InfoCard(
    modifier: Modifier,
    text: String,
    color: Color = Color.Gray,
    icon: Int = 0,
    tint: Color = Color.White
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.size(20.dp),
        painter = painterResource(id = icon),
        tint = tint,
        contentDescription = null
    )

    Text(
        text = text,
        modifier = Modifier.padding(
            start = 12.dp,
            top = 6.dp,
            bottom = 6.dp
        ),
        color = color,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.overline,
        textAlign = TextAlign.Start
    )
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun InfoCardPreviewLight() {
    InfoCard(
        text = "Info",
        icon = R.drawable.ic_up_arrow,
        modifier = Modifier,
        color = Color.DarkGray,
        tint = Color.DarkGray
    )
}
