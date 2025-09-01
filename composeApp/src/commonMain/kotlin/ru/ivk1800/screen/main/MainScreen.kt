package ru.ivk1800.screen.main

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.ivk1800.SampleDescriptor
import ru.ivk1800.navigation.LocalNavHostController
import ru.ivk1800.navigation.SampleDestination
import ru.ivk1800.resources.Res
import ru.ivk1800.resources.app_title
import ru.ivk1800.theme.LocalThemeIsDark
import ru.ivk1800.utils.DarkMode
import ru.ivk1800.utils.Github

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                val uriHandler = LocalUriHandler.current
                IconButton(
                    onClick = {
                        uriHandler.openUri("https://github.com/ivk1800/recomposition-visualization")
                    },
                ) {
                    Icon(
                        imageVector = Github,
                        contentDescription = "Github",
                    )
                }
                val themeIsDark = LocalThemeIsDark.current
                IconButton(
                    onClick = {
                        themeIsDark.value = !themeIsDark.value
                    },
                ) {
                    Icon(
                        imageVector = DarkMode,
                        contentDescription = "DarkMode",
                    )
                }
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            val navController = LocalNavHostController.current

            LazyColumn(
                modifier = Modifier.widthIn(max = 600.dp).align(Alignment.Center),
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        text = stringResource(Res.string.app_title),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center,
                    )
                }

                val items = SampleDescriptor.entries.toList()
                itemsIndexed(items = items) { index, sampleId ->
                    ListItem(
                        modifier = Modifier.clickable {
                            navController.navigate(SampleDestination(sampleId))
                        },
                        headlineContent = {
                            Text("${index + 1}. ${stringResource(sampleId.title)}")
                        },
                    )
                    HorizontalDivider(modifier = Modifier.padding(end = LocalScrollbarStyle.current.thickness))
                }
            }
        }
    }
}
