package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.ivk1800.Highlighted

@Highlighted
object StaticCompositionLocalSample {
    @Composable
    fun EntryPoint() {
        val count = remember { mutableIntStateOf(0) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(
                onClick = { count.value++ },
                content = { Text("Increment") },
            )
            CompositionLocalProvider(LocalCounter provides count.value) {
                RedBox()
                GreenBox()
            }
        }
    }

    @Composable
    fun RedBox() {
        Box(Modifier.background(Color.Red)) {
            Text(text = "Count: ${LocalCounter.current}")
        }
    }

    @Composable
    fun GreenBox() {
        Box(Modifier.background(Color.Green)) {
            Text(text = "Green")
        }
    }

    private val LocalCounter = staticCompositionLocalOf { 0 }
}
