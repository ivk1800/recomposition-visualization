package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ivk1800.Highlighted
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Highlighted
object LazyListWithoutKeyBadSample {

    data class Item(val id: Int)

    @Composable
    fun EntryPoint() {
        Column(modifier = Modifier.fillMaxSize()) {
            val state = rememberLazyListState()

            var items = remember { mutableStateListOf<Item>() }

            LazyColumn(state = state, modifier = Modifier.height(500.dp)) {
                items(
                    count = items.size,
                ) { index ->
                    val item = items[index]
                    if (index % 2 == 0) {
                        Even(item)
                    } else {
                        Odd(item)
                    }
                }
            }
            Row {
                Button(
                    onClick = {
                        items.add(0, Item(items.size))
                    },
                    content = { Text("Add") },
                )
                Button(
                    onClick = { items.clear() },
                    content = { Text("Reset") },
                )
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    @Composable
    fun Odd(item: Item) {
        Box(Modifier.background(Color.Red)) {
            val time = remember { Clock.System.now().toEpochMilliseconds() }
            Text(text = "Item: ${item.id}, $time")
        }
    }

    @OptIn(ExperimentalTime::class)
    @Composable
    fun Even(item: Item) {
        Box(Modifier.background(Color.Yellow)) {
            val time = remember { Clock.System.now().toEpochMilliseconds() }
            Text(text = "Item: ${item.id}, $time")
        }
    }
}
