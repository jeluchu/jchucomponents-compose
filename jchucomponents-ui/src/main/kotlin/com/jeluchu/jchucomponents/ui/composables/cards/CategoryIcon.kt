package com.jeluchu.jchucomponents.ui.composables.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.compose.toImageVector
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.themes.artichoke
import com.jeluchu.jchucomponents.ui.themes.cosmicLatte

@Composable
fun CategoryIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    categoryIconColors: CategoryIconColors = CategoryIconColors(),
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) = Surface(
    modifier
        .size(60.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(color = categoryIconColors.containerColor)
        .clickable(onClick = onClick)
) {
    Icon(
        modifier = Modifier.padding(16.dp),
        imageVector = icon.toImageVector(),
        tint = categoryIconColors.contentColor,
        contentDescription = contentDescription
    )
}

@Immutable
data class CategoryIconColors(
    val contentColor: Color = artichoke,
    val containerColor: Color = cosmicLatte
)

@Preview
@Composable
fun CategoryIconPreview() {
    CategoryIcon(
        icon = R.drawable.ic_btn_qrcode,
    )
}