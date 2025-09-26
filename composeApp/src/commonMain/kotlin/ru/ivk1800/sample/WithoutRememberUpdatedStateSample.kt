package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.ivk1800.Highlighted

@Highlighted
object WithoutRememberUpdatedStateSample {
    @Composable
    fun EntryPoint() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var angle by remember { mutableStateOf(0F) }

            Box(modifier = Modifier.size(100.dp).graphicsLayer { rotationZ = angle }.background(Color.Red))
            Spacer(modifier = Modifier.size(100.dp))

            var rightDirection = remember<(Float) -> Unit> { { newAngle -> angle = newAngle } }
            var leftDirection = remember<(Float) -> Unit> { { newAngle -> angle = -newAngle } }
            var currentDirection by remember { mutableStateOf(rightDirection) }

            AngleProducer(currentDirection)

            Button(
                onClick = {
                    currentDirection = if (currentDirection == rightDirection) {
                        leftDirection
                    } else {
                        rightDirection
                    }
                },
                content = {
                    Text(text = "Change direction")
                },
            )
        }
    }

    @Composable
    private fun AngleProducer(onAngleChanged: (Float) -> Unit) {
        LaunchedEffect(Unit) {
            var angle = 0F
            while (true) {
                delay(250)
                angle += 10
                onAngleChanged.invoke(angle)
            }
        }
    }
}
