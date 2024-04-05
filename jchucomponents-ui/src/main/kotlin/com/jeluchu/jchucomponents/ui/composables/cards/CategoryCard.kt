package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.colors.opacity
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.chips.Type
import com.jeluchu.jchucomponents.ui.composables.chips.TypeColors
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    enabled: Boolean = true,
    textColor: Color = Color(0xFF8B7E6D),
    fontSize: TextUnit = 11.sp,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    backgroundColor: Color = Color(0xFFD4D5C3),
    onClick: () -> Unit = {}
) = Box(
    modifier = modifier
        .height(110.dp)
        .clip(16.cornerRadius())
        .background(backgroundColor.opacity(enabled))
        .clickable(onClick = onClick)
        .padding(10.dp)
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = icon),
        contentDescription = String.empty(),
        tint = textColor.opacity(enabled),
        modifier = Modifier
            .size(35.dp)
            .align(Alignment.TopStart)
    )
    Spacer(modifier = Modifier.align(Alignment.Center))
    Type(
        modifier = Modifier.align(Alignment.BottomEnd),
        text = title,
        colors = TypeColors(
            container = textColor.opacity(enabled)
        ),
        fontSize = fontSize,
        style = textStyle
    )
}

@Preview
@Composable
fun CategoryCardPreview() = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    CategoryCard(
        modifier = Modifier.weight(1f),
        icon = R.drawable.ic_btn_qrcode,
        title = "Hello world!"
    )
    CategoryCard(
        modifier = Modifier.weight(1f),
        icon = R.drawable.ic_btn_qrcode,
        title = "Text",
        enabled = false
    )
}