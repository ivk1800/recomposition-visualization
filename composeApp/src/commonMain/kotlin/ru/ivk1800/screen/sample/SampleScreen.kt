package ru.ivk1800.screen.sample

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.ivk1800.InteractiveSample
import ru.ivk1800.SampleDescriptor
import ru.ivk1800.app.AppBar
import ru.ivk1800.navigation.LocalNavHostController
import ru.ivk1800.navigation.SampleDestination
import ru.ivk1800.resources.Res
import ru.ivk1800.resources.explanation
import ru.ivk1800.resources.how_good

@Composable
fun SampleScreen(sample: SampleDescriptor) {
    var showExplanationDialog by remember { mutableStateOf(false) }
    val explanation = sample.explanation

    val navController = LocalNavHostController.current

    Scaffold(
        topBar = {
            AppBar(
                title = {
                    Text(text = stringResource(sample.title))
                },
                actions = {
                    if (sample.good != null) {
                        OutlinedButton(
                            onClick = {
                                navController.navigate(SampleDestination(sample.good))
                            },
                            content = {
                                Text(stringResource(Res.string.how_good))
                            },
                        )
                    }
                    if (explanation != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = {
                                showExplanationDialog = true
                            },
                            content = {
                                Text(stringResource(Res.string.explanation))
                            },
                        )
                    }
                },
            )
        },
    ) {
        InteractiveSample(
            modifier = Modifier.padding(it),
            sample = sample.entryPoint,
            code = sample.sourceCode,
        )
    }

    if (showExplanationDialog && explanation != null) {
        AlertDialog(
            onDismissRequest = { showExplanationDialog = false },
            confirmButton = {
                TextButton(
                    onClick = { showExplanationDialog = false },
                    content = { Text("ОK") },
                )
            },
            text = { Text(stringResource(explanation)) },
        )
    }
}
