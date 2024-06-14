@file:Suppress("ktlint:standard:no-wildcard-imports")

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import screen.ExampleScreen

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(ExampleScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
