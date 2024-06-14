@file:Suppress("ktlint:standard:filename", "ktlint:standard:no-wildcard-imports")

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import api.ApiService
import kotlinx.coroutines.launch

fun main() =
    application {
        var secondWindowOpened by remember { mutableStateOf(true) }
        var apiResponse by remember { mutableStateOf("Waiting") }
        val scope = rememberCoroutineScope()

        Window(
            onCloseRequest = ::exitApplication,
            title = "DesktopApp",
        ) {
            App()
            if (secondWindowOpened) {
                Window(
                    onCloseRequest = { secondWindowOpened = false },
                    title = "New window",
                    state = WindowState(width = 300.dp, height = 600.dp),
                    resizable = false,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = {
                            scope.launch {
                                apiResponse = ApiService().fetchData()
                            }
                        }) {
                            Text(text = "Click me")
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = apiResponse)
                    }
                }
            }
        }
    }
