package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.ivk1800.Highlighted

@Highlighted
object ImmutableTypeGoodSample {

    @Immutable
    data class MyData(
        val items: List<String>,
    )

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
                content = { Text("Make recomposition") },
            )
        }
        count.value
        RedBox(data = MyData(listOf("test")))
    }

    @Composable
    fun RedBox(data: MyData) {
        Box(Modifier.background(Color.Red)) {
            Text(text = "${data.hashCode()}")
        }
    }
}
