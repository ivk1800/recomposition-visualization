package ru.ivk1800.navigation

import kotlinx.serialization.Serializable
import ru.ivk1800.SampleDescriptor

@Serializable
sealed interface AppDestination

@Serializable
data object MainDestination : AppDestination

@Serializable
data class SampleDestination(val sample: SampleDescriptor) : AppDestination
