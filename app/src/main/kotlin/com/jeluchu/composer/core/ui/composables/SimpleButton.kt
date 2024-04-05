package com.jeluchu.composer.core.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SimpleButton(
    label: String,
    color: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .clickable(
            enabled = true,
            role = Role.Button,
            onClick = onClick,
        )
        .padding(10.dp),
    contentAlignment = Alignment.CenterStart,
) {
    Text(
        text = label,
        color = color,
        fontWeight = FontWeight.Bold
    )
}