package ru.ivk1800.app

import androidx.compose.runtime.staticCompositionLocalOf

val LocalApplicationDependencies = staticCompositionLocalOf<ApplicationDependencies> {
    error("ApplicationDependencies not provided")
}
