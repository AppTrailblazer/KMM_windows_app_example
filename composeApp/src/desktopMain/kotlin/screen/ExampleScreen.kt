@file:Suppress("ktlint:standard:no-wildcard-imports")

package screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class ExampleScreen : Screen {
    @Composable
    override fun Content() {
        ScrollableList()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ScrollableList() {
    val verticalScroll = rememberScrollState(0)
    val horizontalScroll = rememberScrollState(0)

    Box {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(end = 12.dp, bottom = 12.dp)
                    .verticalScroll(verticalScroll)
                    .horizontalScroll(horizontalScroll),
        ) {
            for (item in 0..30) {
                Text(
                    modifier = Modifier.padding(all = 12.dp),
                    text = "This is the item number: #$item",
                )
            }
        }
        VerticalScrollbar(
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(verticalScroll),
        )
        HorizontalScrollbar(
            modifier =
                Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(end = 12.dp),
            adapter = rememberScrollbarAdapter(horizontalScroll),
        )
    }
}
