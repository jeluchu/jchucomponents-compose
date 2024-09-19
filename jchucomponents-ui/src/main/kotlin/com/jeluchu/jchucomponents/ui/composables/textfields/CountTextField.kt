/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

package com.jeluchu.jchucomponents.ui.composables.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.jchucomponents.ktx.strings.empty
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf

/**
 *
 * Author: @Jeluchu
 *
 * This component is based on EditText in which you can check
 * how many characters you have typed and what is the maximum
 *
 * @sample CountTextFieldPreview
 *
 * @param title title to be displayed at the top of the EditText / TextField
 * @param maxLength maximum number of characters the user can type
 * @param countField from here you can customise the text and TextField colours
 * @param styleLabel customise the style of the text
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountTextField(
    title: String,
    maxLength: Int,
    countField: CountField = CountField(),
    styleLabel: TextStyle = LocalTextStyle.current
) = Column {

    var textState by rememberMutableStateOf(String.empty())

    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start,
        color = countField.counterTextColor,
        style = styleLabel
    )

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = countField.backgroundColor,
            cursorColor = countField.cursorColor,
            disabledLabelColor = countField.disabledLabelColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            if (it.length <= maxLength) textState = it
        },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        textStyle = styleLabel,
        trailingIcon = {
            if (textState.isNotEmpty()) {
                IconButton(onClick = { textState = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            }
        }
    )
    Text(
        text = "${textState.length} / $maxLength",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        textAlign = TextAlign.End,
        color = countField.counterTextColor,
        style = styleLabel
    )
}

@Immutable
class CountField constructor(
    val cursorColor: Color = Color.DarkGray,
    val counterTextColor: Color = Color.DarkGray,
    val disabledLabelColor: Color = Color.DarkGray,
    val backgroundColor: Color = Color.White
)

@Preview
@Composable
fun CountTextFieldPreview() {
    CountTextField(
        title = "Name",
        maxLength = 110,
        countField = CountField(
            backgroundColor = Color(0xffd8e6ff),
            disabledLabelColor = Color(0xffd8e6ff),
            counterTextColor = Color(0xff76a9ff)
        )
    )
}