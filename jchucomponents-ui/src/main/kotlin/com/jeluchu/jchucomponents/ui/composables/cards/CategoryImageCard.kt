package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius
import com.jeluchu.jchucomponents.ui.extensions.toImageVector
import com.jeluchu.jchucomponents.ui.extensions.toStringRes

@Composable
fun CategoryImageCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    shape: Dp = 30.dp,
    colors: CardColors = CardColors(),
    onClick: () -> Unit
) = Card(
    colors = colors,
    onClick = onClick,
    modifier = modifier,
    shape = shape.value.toInt().cornerRadius(),
    verticalArrangement = Arrangement.spacedBy(5.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(bottom = 10.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = shape / 2,
                    topStart = shape / 2,
                    bottomStart = shape / 2
                )
            )
            .background(Color.Red)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(
                    color = colors.containerColor,
                    shape = RoundedCornerShape(topStart = shape / 2)
                )
                .padding(10.dp),
            imageVector = com.jeluchu.jchucomponents.ktx.R.drawable.ic_btn_share.toImageVector(),
            contentDescription = null
        )
    }

    Text(
        text = title,
        fontWeight = FontWeight.Bold
    )

    Text(
        maxLines = 2,
        text = description,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(bottom = 5.dp),
        color = colors.contentColor.copy(.7f)
    )
}

@Preview
@Composable
fun CategoryImageCardPreview() = CategoryImageCard(
    modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    title = R.string.sample_title.toStringRes(),
    description = R.string.sample_description.toStringRes()
) {}