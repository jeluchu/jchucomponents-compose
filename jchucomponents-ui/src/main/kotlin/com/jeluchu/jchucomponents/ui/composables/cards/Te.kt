package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.compose.toPainter
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.composables.images.NetworkImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Te(
    image: String,
    width: Dp = 270.dp,
    height: Dp = 340.dp,
    tag: String? = null,
    onClick: () -> Unit = {},
    colors: TeColors = TeColors(),
    onPrimaryButtonClick: () -> Unit = {},
    onSecondaryButtonClick: () -> Unit = {}
) = Card(
    modifier = Modifier.width(width).height(height),
    shape = RoundedCornerShape(28.dp),
    elevation = 0.dp,
    onClick = onClick
) {
    Box {
        NetworkImage(
            modifier = Modifier.fillMaxSize(),
            url = image
        )

        tag?.let { tag ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Card(
                    backgroundColor = MaterialTheme.colors.secondary,
                    shape = RoundedCornerShape(9.dp),
                    modifier = Modifier
                        .size(height = 30.dp, width = 100.dp),
                    elevation = 0.dp
                ) {
                    Text(
                        text = tag,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSecondary,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onPrimaryButtonClick,
                    modifier = Modifier
                        .width(176.dp)
                        .height(40.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp,
                    ),
                    shape = RoundedCornerShape(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colors.containerPrimaryButtonColor
                    )
                ) {
                    Text(
                        text = "Bid Now",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = colors.contentPrimaryButtonColor
                    )
                }
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = colors.containerSecondaryButtonColor,
                    contentColor = colors.contentSecondaryButtonColor,
                    onClick = onPrimaryButtonClick
                ) {
                    Icon(
                        painter = R.drawable.ic_btn_qrcode.toPainter(),
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Immutable
data class TeColors(
    val containerPrimaryButtonColor: Color = Color.DarkGray,
    val contentPrimaryButtonColor: Color = Color.White,
    val containerSecondaryButtonColor: Color = Color.LightGray,
    val contentSecondaryButtonColor: Color = Color.DarkGray,
)

@Preview
@Composable
fun TePreview() {
    Te(
        image = "https://raw.githubusercontent.com/jeluchu/jeluchu.github.io/master/assets/img/home/project-img-2.png"
    )

    Te(
        image = "https://raw.githubusercontent.com/jeluchu/jeluchu.github.io/master/assets/img/home/project-img-2.png",
        tag = "Tendencia"
    )
}