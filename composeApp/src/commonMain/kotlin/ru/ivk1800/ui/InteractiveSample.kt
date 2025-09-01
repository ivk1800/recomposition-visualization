package ru.ivk1800

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InteractiveSample(
    sample: @Composable () -> Unit,
    code: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(16.dp),
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant),
        ) {
            sample.invoke()
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant),
        ) {
            val scrollState = rememberScrollState()

            Box(modifier = Modifier.verticalScroll(scrollState)) {
                code.invoke()
            }
        }
    }
}
