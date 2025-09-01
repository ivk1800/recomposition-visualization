package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ivk1800.Highlighted

@Highlighted
object WithoutDerivedStateOfSample {
    @Composable
    fun EntryPoint() {
        Column(modifier = Modifier.fillMaxSize()) {
            val state = rememberLazyListState()
            LazyColumn(state = state, modifier = Modifier.height(200.dp)) {
                items(20) { index -> ListItem(headlineContent = { Text("Item: $index") }) }
            }
            val background = if (state.firstVisibleItemIndex > 0) Color.Red else Color.Green
            Box(modifier = Modifier.background(background).height(50.dp).fillMaxWidth())
        }
    }
}
