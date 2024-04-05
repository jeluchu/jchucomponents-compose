package com.jeluchu.jchucomponents.ui.composables.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.jchucomponents.ui.R
import com.jeluchu.jchucomponents.ui.runtime.remember.rememberMutableStateOf

@Composable
fun ProgressIndicatorButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    isLoading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    textSyle: TextStyle = MaterialTheme.typography.bodyLarge,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    onClick: () -> Unit,
) = FilledTonalButton(
    modifier = modifier,
    onClick = onClick,
    colors = colors,
    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
) {
    if (isLoading)
        Box(modifier = Modifier.size(18.dp)) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center),
                strokeWidth = 3.dp,
            )
        }
    else Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(18.dp),
    )
    Text(
        text = text,
        modifier = Modifier.padding(start = 8.dp),
        style = textSyle,
        fontSize = fontSize
    )
}

@Preview
@Composable
fun ProgressIndicatorButtonPreview() {
    var isLoading by rememberMutableStateOf(value = false)
    ProgressIndicatorButton(
        isLoading = isLoading,
        text = "Test",
        icon = ImageVector.vectorResource(id = R.drawable.ic_btn_qrcode),
        onClick = { isLoading = !isLoading }
    )
}