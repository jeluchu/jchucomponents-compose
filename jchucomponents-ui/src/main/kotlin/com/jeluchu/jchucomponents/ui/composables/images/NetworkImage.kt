/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.images

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.Transformation
import com.jeluchu.jchucomponents.ktx.compose.toPainter
import com.jeluchu.jchucomponents.ui.R

/**
 *
 * Author: @Jeluchu
 *
 * This component is used to return [ImageRequest.Builder]
 *
 */
fun Context.imageBuilder() = ImageRequest.Builder(this)

/**
 *
 * Author: @Jeluchu
 *
 * This component is used to upload images via network
 *
 * @param image link/resource to image requiring (based on Coil)
 * @param modifier custom modifier for the displayed icon (currently there is a default padding)
 * @param contentScale type of scale for the image
 *
 */

@Composable
fun NetworkImage(
    image: Any,
    modifier: Modifier = Modifier,
    isCrossfade: Boolean = true,
    isAllowHardware: Boolean = true,
    alpha: Float = DefaultAlpha,
    @DrawableRes loading: Int = R.drawable.ic_deco_jeluchu,
    @DrawableRes error: Int = R.drawable.ic_deco_jeluchu,
    transformations: List<Transformation> = emptyList(),
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null
) = AsyncImage(
    modifier = modifier,
    model = ImageRequest.Builder(LocalContext.current)
        .data(image)
        .transformations(transformations)
        .crossfade(isCrossfade)
        .allowHardware(isAllowHardware)
        .error(error)
        .build(),
    alpha = alpha,
    placeholder = loading.toPainter(),
    error = error.toPainter(),
    contentScale = contentScale,
    contentDescription = null
)

/**
 *
 * Author: @Jeluchu
 *
 * This component is used to upload images via network
 *
 * @param url link/resource to image requiring (based on Coil)
 * @param modifier custom modifier for the displayed icon (currently there is a default padding)
 * @param contentScale type of scale for the image
 *
 */

@Composable
fun NetworkImage(
    url: Any,
    modifier: Modifier = Modifier,
    isCrossfade: Boolean = true,
    isAllowHardware: Boolean = true,
    alpha: Float = DefaultAlpha,
    transformations: List<Transformation> = emptyList(),
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null
) = AsyncImage(
    modifier = modifier,
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .transformations(transformations)
        .crossfade(isCrossfade)
        .allowHardware(isAllowHardware)
        .build(),
    alpha = alpha,
    contentScale = contentScale,
    contentDescription = null
)