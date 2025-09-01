package ru.ivk1800.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.ivk1800.SampleDescriptor
import ru.ivk1800.navigation.LocalNavHostController
import ru.ivk1800.navigation.MainDestination
import ru.ivk1800.navigation.SampleDestination
import ru.ivk1800.utils.navTypeOf
import kotlin.reflect.typeOf

@Composable
fun AppContent() {
    val navController = LocalNavHostController.current
    val applicationDependencies = LocalApplicationDependencies.current
    NavHost(
        navController = navController,
        startDestination = MainDestination,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(200))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(200))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(200))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(200))
        },

        ) {
        composable<MainDestination> {
            applicationDependencies.mainScreenFactory.create()
        }
        composable<SampleDestination>(
            typeMap = mapOf(typeOf<SampleDescriptor>() to navTypeOf<SampleDescriptor>()),
        ) {
            val destination = it.toRoute<SampleDestination>()
            applicationDependencies.sampleScreenFactory.create(destination.sample)
        }
    }
}
