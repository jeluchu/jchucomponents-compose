package com.jeluchu.jchucomponents.ui.composables.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.request.transformations
import com.jeluchu.jchucomponents.ui.composables.images.transformations.BlurTransformation

@Composable
fun BlurImage(
    url: Any,
    modifier: Modifier = Modifier,
    isCrossfade: Boolean = true,
    isAllowHardware: Boolean = true,
    alpha: Float = DefaultAlpha,
    blurTransformation: BlurImageTransformation = BlurImageTransformation(),
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null
) = AsyncImage(
    modifier = modifier,
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .transformations(
            BlurTransformation(
                scale = blurTransformation.scale,
                radius = blurTransformation.radius
            )
        )
        .crossfade(isCrossfade)
        .allowHardware(isAllowHardware)
        .build(),
    alpha = alpha,
    contentScale = contentScale,
    contentDescription = null
)

@Immutable
class BlurImageTransformation(
    val scale: Float = 0.5f,
    val radius: Int = 25
)