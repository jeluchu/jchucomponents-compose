/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.images.NetworkImage
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius
import com.jeluchu.jchucomponents.ui.themes.Spacing.sizeSpacing06
import com.jeluchu.jchucomponents.ui.themes.Spacing.sizeSpacing10

/**
 *
 * Author: @Jeluchu
 *
 * Component displaying
 * information on a custom-designed card
 *
 * @param modifier [Modifier] modifier that will be used to change the color, size...
 * @param text [String] for text to be displayed on the card
 * @param image [String] for image to be displayed on the card
 * @param contentColor [Color] color of text that appears on Card
 * @param style [TextStyle] is used to customize the style of text displayed
 *
 */
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    text: String,
    image: String,
    contentColor: Color = Color.Gray,
    style: TextStyle = MaterialTheme.typography.bodySmall
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(sizeSpacing10)
) {
    NetworkImage(
        url = image,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(34.dp)
    )

    Text(
        text = text,
        style = style,
        color = contentColor,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = sizeSpacing06)
    )
}

/**
 *
 * Author: @Jeluchu
 *
 * Component displaying
 * information on a custom-designed card
 *
 * @param modifier [Modifier] modifier that will be used to change the color, size...
 * @param text [String] text to be displayed on the card
 * @param icon [DrawableRes] drawable id of the resource you want to be displayed as an icon on Card
 * @param tint [Color] color of the icon displayed on Card
 *
 */
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
    color: Color = Color.Gray,
    tint: Color = Color.White,
    style: TextStyle = MaterialTheme.typography.bodySmall
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(sizeSpacing10)
) {
    Icon(
        tint = tint,
        contentDescription = null,
        modifier = Modifier.size(18.dp),
        imageVector = icon.toImageVector()
    )

    Text(
        text = text,
        color = color,
        style = style,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = sizeSpacing06)
    )
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun InfoCardPreviewLight() {
    Column {
        InfoCard(
            text = "Info",
            icon = R.drawable.ic_up_arrow,
            modifier = Modifier,
            color = Color.DarkGray,
            tint = Color.DarkGray
        )

        InfoCard(
            text = "Info",
            icon = R.drawable.ic_up_arrow,
            modifier = Modifier
                .clip(10.cornerRadius())
                .background(Color.Blue.copy(.4f))
                .padding(
                    vertical = 5.dp,
                    horizontal = 10.dp
                ),
            color = Color.DarkGray,
            tint = Color.DarkGray
        )
    }
}