package com.jeluchu.jchucomponents.ui.composables.preferences

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.colors.opacity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PreferenceItem(
    title: String,
    description: String? = null,
    icon: Any? = null,
    enabled: Boolean = true,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    onClick: () -> Unit = {},
) = Surface(
    modifier = Modifier.combinedClickable(
        onClick = onClick,
        onClickLabel = onClickLabel,
        enabled = enabled,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick
    )
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (icon) {
            is ImageVector -> {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.opacity(enabled)
                )
            }

            is Painter -> {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.opacity(enabled)
                )
            }

            is Int -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(24.dp)
                        .padding(2.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = if (icon == null) 12.dp else 0.dp)
                .padding(end = 8.dp)
        ) {
            PreferenceItemTitle(text = title, enabled = enabled)
            if (description != null) PreferenceItemDescription(
                text = description,
                enabled = enabled
            )
        }
    }
}

@Composable
@Preview
fun PreferenceItemPreview() {
    Column {
        PreferenceItem(title = "title", description = "description", icon = 0, enabled = false)
        PreferenceItem(title = "title", description = "description", icon = Icons.Outlined.Update)
    }
}

