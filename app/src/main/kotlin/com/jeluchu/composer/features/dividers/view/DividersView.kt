package com.jeluchu.composer.features.dividers.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jeluchu.composer.core.ui.theme.milky
import com.jeluchu.composer.core.ui.theme.primary
import com.jeluchu.composer.core.ui.theme.secondary
import com.jeluchu.composer.core.utils.Names
import com.jeluchu.jchucomponents.ui.accompanist.systemui.SystemStatusBarColors
import com.jeluchu.jchucomponents.ui.composables.column.ScrollableColumn
import com.jeluchu.jchucomponents.ui.composables.divider.DashedDividerInteractivePreview
import com.jeluchu.jchucomponents.ui.composables.divider.DashedDividerStaticPreview

@Composable
fun DividersView() {
    SystemStatusBarColors(
        systemBarsColor = secondary,
        statusBarColor = secondary
    )

    Dividers()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Dividers() = Scaffold(
    topBar = {
        TopAppBar(
            title = {
                Text(
                    text = Names.dividers,
                    color = milky,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = secondary
            )
        )
    },
    containerColor = secondary
) { contentPadding ->
    ScrollableColumn(
        modifier = Modifier.padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Static samples",
            modifier = Modifier
                .fillMaxWidth()
                .background(primary)
                .padding(15.dp),
            fontWeight = FontWeight.Bold
        )

        DashedDividerStaticPreview()

        Text(
            text = "Interactive sample",
            modifier = Modifier
                .fillMaxWidth()
                .background(primary)
                .padding(15.dp),
            fontWeight = FontWeight.Bold
        )

        DashedDividerInteractivePreview()
    }
}