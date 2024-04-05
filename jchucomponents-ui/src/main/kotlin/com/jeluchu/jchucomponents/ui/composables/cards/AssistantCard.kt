package com.jeluchu.jchucomponents.ui.composables.cards
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.extensions.modifier.bounceClick

@Composable
fun AssistantCard(
    image: Int,
    color: Color,
    name: String,
    description: String,
    onClick: () -> Unit
) = Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(10.dp),
    modifier = Modifier
        .padding(5.dp)
        .bounceClick(onClick = onClick)
        .size(width = 170.dp, height = 200.dp)
        .height(200.dp)
        .background(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colors.onSecondary)
        .border(2.dp, color = MaterialTheme.colors.onPrimary, shape = RoundedCornerShape(16.dp))
        .padding(16.dp)
) {
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
        color = MaterialTheme.colors.surface,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            //fontFamily = Urbanist,
            lineHeight = 25.sp
        )
    )
    Text(
        text = description,
        color = MaterialTheme.colors.onSurface,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            //fontFamily = Urbanist,
            lineHeight = 20.sp
        )
    )
}