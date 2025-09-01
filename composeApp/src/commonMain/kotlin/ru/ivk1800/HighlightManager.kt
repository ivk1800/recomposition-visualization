package ru.ivk1800

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

object HighlightManager {
    private val _events = MutableSharedFlow<Event>(extraBufferCapacity = Int.MAX_VALUE)
    val events: Flow<Event> = _events

    fun dispatch(event: Event) {
        _events.tryEmit(event)
    }

    data class Event(
        val startOffset: Int,
        val endOffset: Int,
    )
}

fun highlight(
    startOffset: Int,
    endOffset: Int,
) {
    HighlightManager.dispatch(
        HighlightManager.Event(
            startOffset = startOffset,
            endOffset = endOffset,
        ),
    )
}
