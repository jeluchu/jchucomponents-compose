package com.jeluchu.jchucomponents.ui.extensions

import androidx.compose.runtime.Composable
import com.jeluchu.jchucomponents.ktx.R
import com.jeluchu.jchucomponents.ktx.constants.GIGA_BYTES
import com.jeluchu.jchucomponents.ktx.constants.MEGA_BYTES

@Composable
fun Long.toFileSizeText() = this.toFloat().run {
    if (this > GIGA_BYTES)
        R.string.filesize_gb.toStringRes().format(this / GIGA_BYTES)
    else R.string.filesize_mb.toStringRes().format(this / MEGA_BYTES)
}