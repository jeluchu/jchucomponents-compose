package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.chips.Type
import com.jeluchu.jchucomponents.ui.composables.chips.TypeColors
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun AssistantCard(
    image: Int,
    color: Color,
    name: String,
    description: String,
    onClick: () -> Unit
) = Box(
    modifier = Modifier
        .clip(16.cornerRadius())
        .clickable(
            onClick = onClick,
            role = Role.Button
        )
        .size(140.dp)
        .height(200.dp)
        .background(MaterialTheme.colorScheme.onPrimary)
        .padding(15.dp)
) {
    Image(
        painter = painterResource(image),
        contentDescription = String.empty(),
        modifier = Modifier
            .size(width = 50.dp, height = 50.dp)
            .background(shape = 10.cornerRadius(), color = color)
            .padding(10.dp)
    )


    Type(
        modifier = Modifier.align(Alignment.BottomStart),
        text = name,
        colors = TypeColors(
            //content = colors.contentColor,
            //container = colors.tint.opacity(enabled)
        ),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            //fontFamily = Urbanist,
            lineHeight = 25.sp
        )
    )
}

@Preview
@Composable
fun AssistantCardPreview() {
    AssistantCard(
        image = R.drawable.ic_btn_qrcode,
        color = Color.DarkGray,
        name = "Test",
        description = "Is a AssistantCard with little information",
        onClick = {}
    )
}