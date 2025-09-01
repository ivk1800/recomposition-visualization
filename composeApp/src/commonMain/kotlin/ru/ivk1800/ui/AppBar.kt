package ru.ivk1800.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import ru.ivk1800.navigation.LocalNavHostController
import ru.ivk1800.utils.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    showNavigationIcon: Boolean = true,
) {
    TopAppBar(
        navigationIcon = if (showNavigationIcon) {
            {
                val navController = LocalNavHostController.current
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(
                        imageVector = ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        } else {
            {}
        },
        title = title,
        actions = actions,
    )
}
