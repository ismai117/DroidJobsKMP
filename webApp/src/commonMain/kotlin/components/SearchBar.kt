package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import org.w3c.dom.events.FocusEvent


@Composable
fun ColumnScope.SearchBarView(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(query){
        if (window.navigator.maxTouchPoints > 0) {
            if (query.isNotBlank()) {
                focusManager.clearFocus()
            }
        }
    }

    Row(
        modifier = modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            .widthIn(max = 1000.dp)
            .height(45.dp)
            .align(Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    color = Color(0xFF8f8fa6),
                    shape = RoundedCornerShape(16.dp)
                )
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        if (window.navigator.maxTouchPoints > 0) {
                            val value = window.prompt("Search by seniority, industry or skill")
                            onQueryChange(value.orEmpty())
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            decorationBox = { innerField ->
                Row(
                    modifier = modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        enabled = false,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            tint = Color(0xFF1C1C23)
                        )
                    }
                    Box(
                        modifier = modifier
                            .fillMaxHeight()
                            .weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (query.isBlank()) {
                            Text(
                                text = "Search by seniority, industry or skill",
                                color = Color(0xFF1C1C23),
                                fontSize = 12.sp,
                                lineHeight = 1.em
                            )
                        } else {
                            innerField.invoke()
                        }
                    }
                    IconButton(
                        onClick = {
                            window.alert("Sorry, voice-to-text functionality is currently unavailable.")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "voice to text",
                            tint = Color(0xFF1C1C23)
                        )
                    }
                    IconButton(
                        onClick = {
                            window.alert("Sorting feature is currently being developed.")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Sort,
                            contentDescription = "sort jobs",
                            tint = Color(0xFF1C1C23)
                        )
                    }
                }
            }
        )
    }
}

