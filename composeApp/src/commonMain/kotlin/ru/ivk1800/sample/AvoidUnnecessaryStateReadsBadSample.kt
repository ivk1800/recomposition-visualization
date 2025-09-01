package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.ivk1800.Highlighted

@Highlighted
object AvoidUnnecessaryStateReadsBadSample {
    @Composable
    fun EntryPoint() {
        Box(modifier = Modifier.fillMaxSize()) {
            var angle by remember { mutableStateOf(0F) }

            LaunchedEffect(Unit) {
                while (true) {
                    delay(500)
                    angle += 10
                }
            }

            Function1(angle = angle, modifier = Modifier.align(Alignment.Center))
        }
    }

    @Composable
    fun Function1(angle: Float, modifier: Modifier = Modifier) {
        Function2(angle, modifier)
    }

    @Composable
    fun Function2(angle: Float, modifier: Modifier = Modifier) {
        Function3(angle, modifier)
    }

    @Composable
    fun Function3(angle: Float, modifier: Modifier = Modifier) {
        RedBox(angle, modifier)
    }

    @Composable
    fun RedBox(angle: Float, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.size(100.dp)
                .rotate(angle)
                .background(Color.Red),
        )
    }
}
