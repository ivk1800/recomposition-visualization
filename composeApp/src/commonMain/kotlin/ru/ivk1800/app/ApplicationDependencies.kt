package ru.ivk1800.app

import ru.ivk1800.screen.main.MainScreenFactory
import ru.ivk1800.screen.sample.SampleScreenFactory

class ApplicationDependencies {
    val mainScreenFactory = MainScreenFactory()
    val sampleScreenFactory = SampleScreenFactory()
}
