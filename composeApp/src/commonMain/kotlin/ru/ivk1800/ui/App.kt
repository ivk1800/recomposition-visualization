package ru.ivk1800

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.ivk1800.app.AppContent
import ru.ivk1800.app.ApplicationDependencies
import ru.ivk1800.app.LocalApplicationDependencies
import ru.ivk1800.navigation.LocalNavHostController
import ru.ivk1800.navigation.SampleDestination
import ru.ivk1800.theme.AppTheme

@Preview
@Composable
internal fun App() = AppTheme {
    val navController: NavHostController = rememberNavController()

    CompositionLocalProvider(
        LocalApplicationDependencies provides remember { ApplicationDependencies() },
        LocalNavHostController provides navController,
    ) {
        AppContent()
    }

    LaunchedEffect(navController) {
        val sampleId = locationManager.href.parameters["sampleId"]?.toIntOrNull()
        if (sampleId != null) {
            val sample = SampleDescriptor.entries.find { sample -> sample.id == sampleId }
            if (sample != null) {
                navController.navigate(SampleDestination(sample))
            }
        }
        navController.currentBackStackEntryFlow
            .collect { entry ->
                // TODO Find best solution
                if (entry.destination.route?.contains("SampleDestination") == true) {
                    val route = entry.toRoute<SampleDestination>()
                    locationManager.setSampleId(route.sample.id)
                } else {
                    locationManager.setSampleId(null)
                }
            }
    }
}
