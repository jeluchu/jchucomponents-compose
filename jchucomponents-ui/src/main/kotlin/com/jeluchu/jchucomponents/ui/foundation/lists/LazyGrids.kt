/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.foundation.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius
import kotlin.math.ceil

/**
 *
 * Author: @Jeluchu
 *
 * A custom layout to recreate a GridLayout which lays elements
 * out vertically in evenly sized columns
 *
 * @param modifier modifier that will be used to change the color, size...
 * @param columns number of columns to be displayed
 * @param content the item to be painted within the layout
 *
 */

@Deprecated("VerticalGrid is deprecated", ReplaceWith("LazyStaticGrid"))
@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) = Layout(
    content = content,
    modifier = modifier
) { measurables, constraints ->
    val itemWidth = constraints.maxWidth / columns
    val itemConstraints = constraints.copy(
        minWidth = itemWidth,
        maxWidth = itemWidth
    )
    val placeables = measurables.map { it.measure(itemConstraints) }
    val columnHeights = Array(columns) { 0 }
    placeables.forEachIndexed { index, placeable ->
        val column = index % columns
        columnHeights[column] += placeable.height
    }
    val height = (columnHeights.maxOrNull() ?: constraints.minHeight)
        .coerceAtMost(constraints.maxHeight)
    layout(
        width = constraints.maxWidth,
        height = height
    ) {
        val columnY = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            placeable.place(
                x = column * itemWidth,
                y = columnY[column]
            )
            columnY[column] += placeable.height
        }
    }
}

@Composable
fun LazyStaticGrid(
    modifier: Modifier = Modifier,
    columns: Int,
    itemCount: Int,
    fillLastRow: Boolean = true,
    rowContentAlignment: RowContentAlignment = RowContentAlignment(),
    columnContentAlignment: ColumnContentAlignment = ColumnContentAlignment(),
    content: @Composable RowScope.(itemIndex: Int) -> Unit,
) = Column(
    modifier = modifier,
    verticalArrangement = columnContentAlignment.verticalArrangement,
    horizontalAlignment = columnContentAlignment.horizontalAlignment
) {
    val rowsCount = ceil(itemCount.toDouble() / columns).toInt()
    repeat(rowsCount) { rowIndex ->
        Row(
            horizontalArrangement = rowContentAlignment.horizontalArrangement,
            verticalAlignment = rowContentAlignment.verticalAlignment
        ) {
            repeat(columns) { columnIndex ->
                val itemIndex = rowIndex * columns + columnIndex
                if (itemIndex < itemCount) content(itemIndex)
                else if (fillLastRow) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun <T> LazyStaticGrid(
    modifier: Modifier = Modifier,
    items: List<T>,
    columns: Int,
    fillLastRow: Boolean = true,
    rowContentAlignment: RowContentAlignment = RowContentAlignment(),
    columnContentAlignment: ColumnContentAlignment = ColumnContentAlignment(),
    content: @Composable RowScope.(itemIndex: T) -> Unit,
) = Column(
    modifier = modifier,
    verticalArrangement = columnContentAlignment.verticalArrangement,
    horizontalAlignment = columnContentAlignment.horizontalAlignment
) {
    val itemsCount = items.size
    val rowsCount = ceil(itemsCount.toDouble() / columns).toInt()
    repeat(rowsCount) { rowIndex ->
        Row(
            horizontalArrangement = rowContentAlignment.horizontalArrangement,
            verticalAlignment = rowContentAlignment.verticalAlignment
        ) {
            repeat(columns) { columnIndex ->
                val itemIndex = rowIndex * columns + columnIndex
                if (itemIndex < itemsCount) content(items[itemIndex])
                else if (fillLastRow) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Immutable
class ColumnContentAlignment(
    val verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    val horizontalAlignment: Alignment.Horizontal = Alignment.Start
)

@Immutable
class RowContentAlignment(
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    val verticalAlignment: Alignment.Vertical = Alignment.Top
)

@Preview
@Composable
fun LazyStaticGridPreview(
    primary: Color = Color(0xFFA9D2B5),
    secondary: Color = Color(0xFF79BA98),
    milky: Color = Color(0xFFF9F8DD)
) = Column(
    verticalArrangement = Arrangement.spacedBy(15.dp)
) {
    Text(text = "LazyStaticGrid with number to repeat")
    LazyStaticGrid(
        columns = 5,
        itemCount = 22,
        modifier = Modifier.padding(bottom = 20.dp)
    ) { itemIndex ->
        Box(
            Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(5.dp)
                .clip(15.cornerRadius())
                .background(primary)
                .padding(8.dp),
            Alignment.Center,
        ) {
            Text(
                text = itemIndex.toString(),
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }

    Text(text = "LazyStaticGrid with simple list of String")
    LazyStaticGrid(
        columns = 3,
        itemCount = listOf(
            "Spanish",
            "Catalan",
            "Galician",
            "Basque",
            "English",
            "Italian",
            "French",
            "Portuguese",
            "Portuguese por tu",
            "Polish",
            "Disabled",
        ).size,
        modifier = Modifier.padding(bottom = 20.dp)
    ) { itemIndex ->
        Box(
            Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(5.dp)
                .clip(15.cornerRadius())
                .background(primary)
                .padding(8.dp),
            Alignment.Center,
        ) {
            Text(
                text = itemIndex.toString(),
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }

    Text(text = "LazyStaticGrid with simple list of custom object")
    LazyStaticGrid(
        columns = 3,
        items = listOf(
            LazyGridPeview(
                id = 0,
                name = "Spanish"
            ),
            LazyGridPeview(
                id = 1,
                name = "Catalan"
            ),
            LazyGridPeview(
                id = 2,
                name = "Galician"
            ),
            LazyGridPeview(
                id = 3,
                name = "Basque"
            ),
            LazyGridPeview(
                id = 4,
                name = "English"
            ),
            LazyGridPeview(
                id = 5,
                name = "Italian"
            )
        ),
        modifier = Modifier.padding(bottom = 20.dp)
    ) { item ->
        Box(
            Modifier
                .weight(1f)
                .aspectRatio(1f)
                .padding(5.dp)
                .clip(15.cornerRadius())
                .background(primary)
                .padding(8.dp),
            Alignment.Center,
        ) {
            Text(
                text = item.name,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }
}

private data class LazyGridPeview(
    val id: Int,
    val name: String
)