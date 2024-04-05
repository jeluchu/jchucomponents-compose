@file:Suppress("unused")

package com.jeluchu.jchucomponents.ui.foundation.lists

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ui.foundation.lists.composition.ListNotOverScroll

/**
 *
 * The vertical scrolling list that only composes and lays out the currently visible items.
 * This composable shows only [Array] of items.
 *
 * @param items the data array
 * @param modifier the modifier to apply to this layout
 * @param key a factory of stable and unique keys representing the item. Using the same key
 * for multiple items in the list is not allowed. The Type of key should be savable
 * via Bundle on Android. If null is passed, the position in the list will represent the key.
 * When you specify the key, the scroll position will be maintained based on the key, which
 * means if you add/remove items before the current visible item, the item with the given key
 * will be kept as the first visible one
 * @param contentType a factory of the content types for the item. The item compositions of
 * the same type could be reused more efficiently. Note that null is a valid type and item of such a
 * type will be considered compatible
 * @param state the state object to be used to control or observe the list's state
 * @param contentPadding a padding around the whole content. This will add padding for the
 * content after it has been clipped, which is not possible via [modifier] param. You can use it
 * to add a padding before the first item or after the last one. If you want to add a spacing
 * between each item use [verticalArrangement]
 * @param reverseLayout reverse the direction of scrolling and layout. When `true`, items are
 * laid out in the reverse order and [LazyListState.firstVisibleItemIndex] == 0 means
 * that column is scrolled to the bottom. Note that [reverseLayout] does not change the behavior of
 * [verticalArrangement],
 * e.g. with [Arrangement.Top] (top) 123### (bottom) becomes (top) 321### (bottom)
 * @param verticalArrangement The vertical arrangement of the layout's children. This allows
 * adding a spacing between items and specifying the arrangement of the items when we have not enough
 * of them to fill the whole minimum size
 * @param horizontalAlignment the horizontal alignment applied to the items
 * @param flingBehavior logic describing fling behavior
 * @param userScrollEnabled whether the scrolling via the user gestures or accessibility actions
 * is allowed. You can still scroll programmatically, using the state even when it is disabled
 * @param itemContent the content displayed by a single item
 */
@Composable
inline fun <T> LazyColumnFor(
    items: Array<T>,
    modifier: Modifier = Modifier,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    crossinline itemContent: @Composable (LazyItemScope.(item: T) -> Unit)
) = LazyColumn(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    reverseLayout = reverseLayout,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    flingBehavior = flingBehavior,
    userScrollEnabled = userScrollEnabled,
    content = { items(items, key, contentType, itemContent) }
)

/**
 *
 * The vertical scrolling list that only composes and lays out the currently visible items.
 * This composable shows only [Array] of items.
 *
 * @param items the data array
 * @param modifier the modifier to apply to this layout
 * @param key a factory of stable and unique keys representing the item. Using the same key
 * for multiple items in the list is not allowed. The Type of key should be savable
 * via Bundle on Android. If null is passed, the position in the list will represent the key.
 * When you specify the key, the scroll position will be maintained based on the key, which
 * means if you add/remove items before the current visible item, the item with the given key
 * will be kept as the first visible one
 * @param contentType a factory of the content types for the item. The item compositions of
 * the same type could be reused more efficiently. Note that null is a valid type and item of such a
 * type will be considered compatible
 * @param state the state object to be used to control or observe the list's state
 * @param contentPadding a padding around the whole content. This will add padding for the
 * content after it has been clipped, which is not possible via [modifier] param. You can use it
 * to add a padding before the first item or after the last one. If you want to add a spacing
 * between each item use [verticalArrangement]
 * @param reverseLayout reverse the direction of scrolling and layout. When `true`, items are
 * laid out in the reverse order and [LazyListState.firstVisibleItemIndex] == 0 means
 * that column is scrolled to the bottom. Note that [reverseLayout] does not change the behavior of
 * [verticalArrangement],
 * e.g. with [Arrangement.Top] (top) 123### (bottom) becomes (top) 321### (bottom)
 * @param verticalArrangement The vertical arrangement of the layout's children. This allows
 * adding a spacing between items and specifying the arrangement of the items when we have not enough
 * of them to fill the whole minimum size
 * @param horizontalAlignment the horizontal alignment applied to the items
 * @param flingBehavior logic describing fling behavior
 * @param userScrollEnabled whether the scrolling via the user gestures or accessibility actions
 * is allowed. You can still scroll programmatically, using the state even when it is disabled
 * @param itemContent the content displayed by a single item
 *
 */
@Composable
inline fun <T> LazyColumnForIndexed(
    items: Array<T>,
    modifier: Modifier = Modifier,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    noinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    crossinline itemContent: @Composable (LazyItemScope.(index: Int, item: T) -> Unit)
) = LazyColumn(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    reverseLayout = reverseLayout,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    flingBehavior = flingBehavior,
    userScrollEnabled = userScrollEnabled,
    content = { itemsIndexed(items, key, contentType, itemContent) }
)

/**
 *
 * The vertical scrolling list that only composes and lays out the currently visible items.
 * This composable shows only [List] of items.
 *
 * @param count items count
 * @param modifier the modifier to apply to this layout
 * @param key a factory of stable and unique keys representing the item. Using the same key
 * for multiple items in the list is not allowed. The Type of key should be savable
 * via Bundle on Android. If null is passed, the position in the list will represent the key.
 * When you specify the key, the scroll position will be maintained based on the key, which
 * means if you add/remove items before the current visible item, the item with the given key
 * will be kept as the first visible one
 * @param contentType a factory of the content types for the item. The item compositions of
 * the same type could be reused more efficiently. Note that null is a valid type and item of such a
 * type will be considered compatible
 * @param state the state object to be used to control or observe the list's state
 * @param contentPadding a padding around the whole content. This will add padding for the
 * content after it has been clipped, which is not possible via [modifier] param. You can use it
 * to add a padding before the first item or after the last one. If you want to add a spacing
 * between each item use [verticalArrangement]
 * @param reverseLayout reverse the direction of scrolling and layout. When `true`, items are
 * laid out in the reverse order and [LazyListState.firstVisibleItemIndex] == 0 means
 * that column is scrolled to the bottom. Note that [reverseLayout] does not change the behavior of
 * [verticalArrangement],
 * e.g. with [Arrangement.Top] (top) 123### (bottom) becomes (top) 321### (bottom)
 * @param verticalArrangement The vertical arrangement of the layout's children. This allows
 * adding a spacing between items and specifying the arrangement of the items when we have not enough
 * of them to fill the whole minimum size
 * @param horizontalAlignment the horizontal alignment applied to the items
 * @param flingBehavior logic describing fling behavior
 * @param userScrollEnabled whether the scrolling via the user gestures or accessibility actions
 * is allowed. You can still scroll programmatically, using the state even when it is disabled
 * @param itemContent the content displayed by a single item
 *
 */
@Composable
fun LazyColumnFor(
    count: Int,
    modifier: Modifier = Modifier,
    key: ((index: Int) -> Any)? = null,
    contentType: (index: Int) -> Any? = { null },
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    itemContent: @Composable LazyItemScope.(index: Int) -> Unit
) = LazyColumn(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    reverseLayout = reverseLayout,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    flingBehavior = flingBehavior,
    userScrollEnabled = userScrollEnabled,
    content = { items(count, key, contentType, itemContent) }
)

/**
 * The vertical scrolling list that only composes and lays out the currently visible items.
 * This composable shows only [List] of items.
 *
 * @param items the data list
 * @param modifier the modifier to apply to this layout
 * @param key a factory of stable and unique keys representing the item. Using the same key
 * for multiple items in the list is not allowed. The Type of key should be savable
 * via Bundle on Android. If null is passed, the position in the list will represent the key.
 * When you specify the key, the scroll position will be maintained based on the key, which
 * means if you add/remove items before the current visible item, the item with the given key
 * will be kept as the first visible one
 * @param contentType a factory of the content types for the item. The item compositions of
 * the same type could be reused more efficiently. Note that null is a valid type and item of such a
 * type will be considered compatible
 * @param state the state object to be used to control or observe the list's state
 * @param contentPadding a padding around the whole content. This will add padding for the
 * content after it has been clipped, which is not possible via [modifier] param. You can use it
 * to add a padding before the first item or after the last one. If you want to add a spacing
 * between each item use [verticalArrangement]
 * @param reverseLayout reverse the direction of scrolling and layout. When `true`, items are
 * laid out in the reverse order and [LazyListState.firstVisibleItemIndex] == 0 means
 * that column is scrolled to the bottom. Note that [reverseLayout] does not change the behavior of
 * [verticalArrangement],
 * e.g. with [Arrangement.Top] (top) 123### (bottom) becomes (top) 321### (bottom)
 * @param verticalArrangement The vertical arrangement of the layout's children. This allows
 * adding a spacing between items and specifying the arrangement of the items when we have not enough
 * of them to fill the whole minimum size
 * @param horizontalAlignment the horizontal alignment applied to the items
 * @param flingBehavior logic describing fling behavior
 * @param userScrollEnabled whether the scrolling via the user gestures or accessibility actions
 * is allowed. You can still scroll programmatically, using the state even when it is disabled
 * @param itemContent the content displayed by a single item
 */
@Composable
inline fun <T> LazyColumnFor(
    items: List<T>,
    modifier: Modifier = Modifier,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    crossinline itemContent: @Composable (LazyItemScope.(item: T) -> Unit)
) = LazyColumn(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    reverseLayout = reverseLayout,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    flingBehavior = flingBehavior,
    userScrollEnabled = userScrollEnabled,
    content = { items(items, key, contentType, itemContent) }
)

/**
 *
 * The vertical scrolling list that only composes and lays out the currently visible items.
 * This composable shows only [List] of items.
 *
 * @param items the data list
 * @param modifier the modifier to apply to this layout
 * @param key a factory of stable and unique keys representing the item. Using the same key
 * for multiple items in the list is not allowed. The Type of key should be savable
 * via Bundle on Android. If null is passed, the position in the list will represent the key.
 * When you specify the key, the scroll position will be maintained based on the key, which
 * means if you add/remove items before the current visible item, the item with the given key
 * will be kept as the first visible one
 * @param contentType a factory of the content types for the item. The item compositions of
 * the same type could be reused more efficiently. Note that null is a valid type and item of such a
 * type will be considered compatible
 * @param state the state object to be used to control or observe the list's state
 * @param contentPadding a padding around the whole content. This will add padding for the
 * content after it has been clipped, which is not possible via [modifier] param. You can use it
 * to add a padding before the first item or after the last one. If you want to add a spacing
 * between each item use [verticalArrangement]
 * @param reverseLayout reverse the direction of scrolling and layout. When `true`, items are
 * laid out in the reverse order and [LazyListState.firstVisibleItemIndex] == 0 means
 * that column is scrolled to the bottom. Note that [reverseLayout] does not change the behavior of
 * [verticalArrangement],
 * e.g. with [Arrangement.Top] (top) 123### (bottom) becomes (top) 321### (bottom)
 * @param verticalArrangement The vertical arrangement of the layout's children. This allows
 * adding a spacing between items and specifying the arrangement of the items when we have not enough
 * of them to fill the whole minimum size
 * @param horizontalAlignment the horizontal alignment applied to the items
 * @param flingBehavior logic describing fling behavior
 * @param userScrollEnabled whether the scrolling via the user gestures or accessibility actions
 * is allowed. You can still scroll programmatically, using the state even when it is disabled
 * @param itemContent the content displayed by a single item
 */
@Composable
inline fun <T> LazyColumnForIndexed(
    items: List<T>,
    modifier: Modifier = Modifier,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    noinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    crossinline itemContent: @Composable (LazyItemScope.(index: Int, item: T) -> Unit)
) = LazyColumn(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    reverseLayout = reverseLayout,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    flingBehavior = flingBehavior,
    userScrollEnabled = userScrollEnabled,
    content = { itemsIndexed(items, key, contentType, itemContent) }
)

@Composable
fun ListColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit
) = ListNotOverScroll {
    LazyColumn(
        state = state,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Composable
fun <T> ListColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    items: List<T> = emptyList(),
    content: @Composable (T) -> Unit
) = ListNotOverScroll {
    LazyColumn(
        state = state,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) { items(items) { item -> content(item) } }
}

@Composable
fun <T> ListColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    items: List<T> = emptyList(),
    content: @Composable (Int, T) -> Unit
) = ListNotOverScroll {
    LazyColumn(
        state = state,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) { itemsIndexed(items) { index, item -> content(index, item) } }
}
