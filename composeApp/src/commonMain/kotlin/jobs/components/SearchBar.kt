package components


import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import theme.LocalThemeIsDark



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SearchBarView(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onMic: () -> Unit,
    onSort: () -> Unit
) {

    val isDark by LocalThemeIsDark.current

    val interactionSource = remember { MutableInteractionSource() }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }


    Surface(
        modifier = modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            .widthIn(max = 1000.dp)
            .height(45.dp)
            .align(Alignment.CenterHorizontally),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {


            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = if (isDark) MaterialTheme.colorScheme.primary else Color.Transparent)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.hasFocus
                    },
                textStyle = TextStyle(
                    color = if (isDark) Color.White else Color.Black,
                    fontSize = 14.sp,
                    lineHeight = 1.em
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                })
            ){
                OutlinedTextFieldDefaults.DecorationBox(
                    value = query,
                    enabled = true,
                    innerTextField = it,
                    interactionSource = interactionSource,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    placeholder = {
                        if (!isFocused){
                            Text(
                                text = "Search by seniority, industry or skill",
                                color = if (isDark) Color.White else Color.Black,
                                fontSize = 14.sp,
                                lineHeight = 1.em
                            )
                        }
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search",
                                tint = if (isDark) Color.White else Color.Black
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onSort()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Sort,
                                contentDescription = "sort jobs",
                                tint = if (isDark) Color.White else Color.Black
                            )
                        }
                    },
                    contentPadding = PaddingValues(12.dp)
                )
            }

        }
    }

}



