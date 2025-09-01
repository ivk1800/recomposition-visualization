package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ivk1800.Highlighted

@Highlighted
object MovableContentOfSample {
    @Composable
    fun EntryPoint() {
        var isHorizontal by remember { mutableStateOf(false) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(
                onClick = { isHorizontal = !isHorizontal },
                content = { Text("Toggle") },
            )
        }

        val boxes = remember {
            movableContentOf {
                RedBox()
                GreenBox()
            }
        }

        if (isHorizontal) {
            Row {
                boxes()
            }
        } else {
            Column {
                boxes()
            }
        }
    }

    @Composable
    fun RedBox() {
        Box(Modifier.background(Color.Red)) {
            val count = remember { mutableIntStateOf(0) }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp),
            ) {
                Button(
                    onClick = { count.value++ },
                    content = { Text("${count.value}") },
                )
            }
        }
    }

    @Composable
    fun GreenBox() {
        Box(Modifier.background(Color.Green)) {
            val count = remember { mutableIntStateOf(0) }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp),
            ) {
                Button(
                    onClick = { count.value++ },
                    content = { Text("${count.value}") },
                )
            }
        }
    }
}
