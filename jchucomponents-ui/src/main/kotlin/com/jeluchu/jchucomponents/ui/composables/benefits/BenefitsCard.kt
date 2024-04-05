package com.jeluchu.jchucomponents.ui.composables.benefits

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun BenefitsCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    benefitsColors: BenefitsColors = BenefitsColors(),
    onClick: () -> Unit = {}
) = Row(
    modifier = modifier
        .clip(shape)
        .clickable { onClick() }
        .background(benefitsColors.containerColor)
        .padding(15.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(15.dp)
) {
    leftIcon?.let { icon ->
        Surface(
            shape = 8.cornerRadius(),
            color = benefitsColors.containerColorBox
        ) {
            Icon(
                tint = benefitsColors.contentColorBox,
                modifier = Modifier.padding(5.dp),
                imageVector = icon.toImageVector(),
                contentDescription = String.empty()
            )
        }
    }

    Column(
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = benefitsColors.contentColor
        )

        description?.let { description ->
            Text(
                fontSize = 12.sp,
                text = description,
                color = benefitsColors.contentColor.copy(.7f),
            )
        }
    }

    rightIcon?.let { icon ->
        Icon(
            tint = benefitsColors.containerColor,
            modifier = Modifier.padding(5.dp),
            imageVector = icon.toImageVector(),
            contentDescription = String.empty()
        )
    }
}

@Immutable
class BenefitsColors(
    val tint: Color = Color.DarkGray,
    val containerColor: Color = Color.White,
    val contentColor: Color = Color.DarkGray,
    val contentColorBox: Color = Color.DarkGray,
    val containerColorBox: Color = Color.LightGray
)

@Preview
@Composable
fun BenefitsCardPreview(
    name: String = "Benefit name",
    description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus pharetra cursus sapien. Sed aliquam tellus nulla, eget congue lectus iaculis."
) = Column(
    modifier = Modifier.padding(horizontal = 10.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    BenefitsCard(
        title = name,
        description = description
    )

    BenefitsCard(
        title = name,
        description = description,
        leftIcon = R.drawable.ic_btn_share,
        rightIcon = R.drawable.ic_btn_qrcode
    )

    BenefitsCard(
        title = name,
        leftIcon = R.drawable.ic_btn_share,
        rightIcon = R.drawable.ic_btn_qrcode
    )
}