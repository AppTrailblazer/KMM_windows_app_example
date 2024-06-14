@file:Suppress("ktlint:standard:no-wildcard-imports")

package screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class ExampleScreen : Screen {
    @Composable
    override fun Content() {
        Tooltips()
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

@Suppress("ktlint:standard:function-naming")
@Composable
fun ScrollableLazyList() {
    @Suppress("ktlint:standard:property-naming")
    val LazyListState = rememberLazyListState()
    val horizontalScroll = rememberScrollState(0)

    Box {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(end = 12.dp, bottom = 12.dp)
                    .horizontalScroll(horizontalScroll),
            state = LazyListState,
        ) {
            items(1000) { number ->
                Text(
                    modifier = Modifier.padding(all = 12.dp),
                    text = "This is the item number: #$number",
                )
            }
        }
        VerticalScrollbar(
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(LazyListState),
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

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tooltips() {
    val buttons = listOf("Contact us", "About")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        buttons.forEachIndexed { index, title ->
            TooltipArea(
                tooltip = {
                    Surface(
                        modifier = Modifier.shadow(8.dp),
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            text =
                                if (index == 0) {
                                    "Get in touch!"
                                } else {
                                    "This is our team."
                                },
                            modifier = Modifier.padding(10.dp),
                        )
                    }
                },
                delayMillis = 600,
                tooltipPlacement =
                    TooltipPlacement.CursorPoint(
                        alignment = Alignment.BottomEnd,
                    ),
            ) {
                Button(onClick = {}) { Text(text = title) }
            }
            if (index == 0) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
