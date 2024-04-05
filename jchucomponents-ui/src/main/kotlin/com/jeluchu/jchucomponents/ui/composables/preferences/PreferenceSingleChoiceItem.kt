package com.jeluchu.jchucomponents.ui.composables.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ui.extensions.modifier.cornerRadius

@Composable
fun PreferenceSingleChoiceItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    shape: Shape = 15.cornerRadius(),
    style: TextStyle = MaterialTheme.typography.titleLarge,
    colors: PreferenceChoiceColors = PreferenceChoiceColors(),
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 18.dp),
    onClick: () -> Unit
) = Surface(
    modifier = Modifier.selectable(
        selected = selected,
        onClick = onClick
    ),
    shape = shape,
    contentColor = colors.contentColor,
    color = colors.containerColor
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = text,
                maxLines = 1,
                style = style.copy(fontSize = 16.sp),
                overflow = TextOverflow.Ellipsis
            )
        }
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.selectedRadioColor,
                unselectedColor = colors.unselectedRadioColor
            ),
            modifier = Modifier
                .padding()
                .clearAndSetSemantics { },
        )
    }
}

@Immutable
class PreferenceChoiceColors(
    val selectedRadioColor: Color = Color.DarkGray,
    val unselectedRadioColor: Color = Color.DarkGray,
    val containerColor: Color = Color.White,
    val contentColor: Color = Color.DarkGray
)


@Preview
@Composable
fun PreferenceSingleChoiceItemPreview() = PreferenceSingleChoiceItem(
    text = "Test",
    selected = true,
    onClick = {}
)