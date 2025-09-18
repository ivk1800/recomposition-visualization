package ru.ivk1800

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import ru.ivk1800.theme.LocalThemeIsDark
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HighlightedText(text: String) {
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    val rectanglesState = remember { mutableStateListOf<Rect>() }

    // TODO Move to theme
    val themeIsDark = LocalThemeIsDark.current
    val highlightColor = remember(themeIsDark.value) {
        if (themeIsDark.value) {
            Color.Yellow
        } else {
            Color.Red
        }
            .copy(alpha = 0.2f)
    }

    Box(modifier = Modifier.padding(16.dp)) {
        Text(
            text = text,
            onTextLayout = { layoutResult.value = it },
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .drawBehind {
                    rectanglesState.forEach { rect ->
                        drawRect(
                            color = highlightColor,
                            topLeft = rect.topLeft,
                            size = rect.size,
                        )
                    }
                },
        )

        LaunchedEffect(Unit) {
            snapshotFlow { layoutResult.value }
                .filterNotNull()
                .flatMapLatest { layoutResult ->
                    HighlightManager.events
                        .mapNotNull { event ->
                            val start = event.startOffset
                            val end = event.endOffset + 1

                            if (end !in layoutResult.multiParagraph.intrinsics.annotatedString.text.indices) {
                                return@mapNotNull null
                            }

                            if (start >= 0) {
                                (start until end).map { index -> layoutResult.getBoundingBox(index) }
                            } else {
                                null
                            }
                        }
                }
                .collect { newRectangles ->
                    launch {
                        rectanglesState.addAll(newRectangles)
                        delay(100.milliseconds)
                        newRectangles.forEach(rectanglesState::remove)
                    }
                }
        }
    }
}
