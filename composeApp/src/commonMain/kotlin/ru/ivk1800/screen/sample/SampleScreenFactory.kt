package ru.ivk1800.screen.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import ru.ivk1800.SampleDescriptor

class SampleScreenFactory {
    @Composable
    fun create(sample: SampleDescriptor) {
        key(sample) {
            SampleScreen(sample)
        }
    }
}
