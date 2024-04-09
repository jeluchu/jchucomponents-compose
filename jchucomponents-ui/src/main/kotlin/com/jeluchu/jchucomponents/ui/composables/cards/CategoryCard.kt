package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.colors.opacity
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.chips.Type
import com.jeluchu.jchucomponents.ui.composables.chips.TypeColors
import com.jeluchu.jchucomponents.ui.extensions.modifier.bounceClick
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    enabled: Boolean = true,
    colors: CategoryColors = CategoryColors(),
    style: TextStyle = MaterialTheme.typography.bodySmall,
    onClick: () -> Unit = {}
) = Box(
    modifier = modifier
        .height(110.dp)
        .clip(16.cornerRadius())
        .background(colors.containerColor.opacity(enabled))
        .clickable(onClick = onClick)
        .padding(10.dp)
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = icon),
        contentDescription = String.empty(),
        tint = colors.tint.opacity(enabled),
        modifier = Modifier
            .size(35.dp)
            .align(Alignment.TopStart)
    )
    Spacer(modifier = Modifier.align(Alignment.Center))
    Type(
        modifier = Modifier.align(Alignment.BottomEnd),
        text = title,
        colors = TypeColors(
            content = colors.contentColor,
            container = colors.tint.opacity(enabled)
        ),
        style = style
    )
}
/*
@Composable
fun CategoryAssistantCard(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    enabled: Boolean = true,
    colors: CategoryColors = CategoryColors(),
    style: TextStyle = MaterialTheme.typography.bodySmall,
    onClick: () -> Unit = {}
) = Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(10.dp),
    modifier = Modifier
        .padding(5.dp)
        .bounceClick(onClick = onClick)
        .size(width = 170.dp, height = 200.dp)
        .height(200.dp)
        .background(shape = RoundedCornerShape(16.dp), color = androidx.compose.material.MaterialTheme.colors.onSecondary)
        .border(2.dp, color = androidx.compose.material.MaterialTheme.colors.onPrimary, shape = RoundedCornerShape(16.dp))
        .padding(16.dp)
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = icon),
        contentDescription = String.empty(),
        tint = colors.tint.opacity(enabled),
        modifier = Modifier
            .size(35.dp)
            .align(Alignment.TopStart)
    )

    Image(
        painter = painterResource(image),
        contentDescription = String.empty(),
        modifier = Modifier
            .size(width = 60.dp, height = 60.dp)
            .background(shape = RoundedCornerShape(16.dp), color = color)
            .padding(10.dp)

    )
    Text(
        text = name,
        color = androidx.compose.material.MaterialTheme.colors.surface,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            //fontFamily = Urbanist,
            lineHeight = 25.sp
        )
    )
    Text(
        text = description,
        color = androidx.compose.material.MaterialTheme.colors.onSurface,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            //fontFamily = Urbanist,
            lineHeight = 20.sp
        )
    )
}*/

@Immutable
class CategoryColors(
    val tint: Color = Color(0xFF8B7E6D),
    val contentColor: Color = Color(0xFFD4D5C3),
    val containerColor: Color = Color(0xFFD4D5C3),
)

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