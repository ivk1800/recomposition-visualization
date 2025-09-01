package ru.ivk1800.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.ivk1800.Highlighted

@Highlighted
object ReadStateGoodSample {
    @Composable
    fun EntryPoint() {
        val countState = remember { mutableIntStateOf(0) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(
                onClick = { countState.value++ },
                content = { Text("Increment ${countState.value}") },
            )
        }
    }
}
