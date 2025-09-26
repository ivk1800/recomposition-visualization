package ru.ivk1800.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.ivk1800.Highlighted

@Highlighted
object LaunchEffectWithoutKeySample {
    @Composable
    fun EntryPoint() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val keyState = remember { mutableIntStateOf(0) }
            val pagerState = key(keyState.value) {
                rememberPagerState(initialPageOffsetFraction = 0F, pageCount = { Int.MAX_VALUE })
            }

            HorizontalPager(
                modifier = Modifier.size(200.dp),
                state = pagerState,
            ) { page ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(if (page % 2 == 0) Color.Red else Color.Green)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "$page",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
            }

            Button(
                onClick = { keyState.value += 1 },
                content = { Text(text = "Reset") },
            )

            LaunchedEffect(Unit) {
                while (true) {
                    delay(1000)
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
    }
}
