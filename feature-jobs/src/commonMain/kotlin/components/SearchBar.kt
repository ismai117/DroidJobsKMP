package components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import theme.LocalThemeIsDark



@Composable
fun ColumnScope.SearchBarView(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onMic: () -> Unit,
    onSort: () -> Unit
) {
    val isDark by LocalThemeIsDark.current
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
                    .background(color = if (isDark) MaterialTheme.colorScheme.primary else Color.Transparent),
                textStyle = TextStyle(
                    color = if (isDark) MaterialTheme.colorScheme.onPrimary else Color.Black,
                    fontSize = 12.sp,
                    lineHeight = 1.em
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                decorationBox = { innerField ->
                    Row(
                        modifier = modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search",
                                tint = if (isDark) MaterialTheme.colorScheme.onPrimary else Color.Black
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
                                    color = if (isDark) MaterialTheme.colorScheme.onPrimary else Color.Black,
                                    fontSize = 12.sp,
                                    lineHeight = 1.em
                                )
                            } else {
                                innerField.invoke()
                            }
                        }
//                        IconButton(
//                            onClick = {
//                                onMic()
//                            }
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Mic,
//                                contentDescription = "voice to text",
//                                tint = if (isDark) MaterialTheme.colorScheme.onPrimary else Color.Black
//                            )
//                        }
                        IconButton(
                            onClick = {
                                onSort()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Sort,
                                contentDescription = "sort jobs",
                                tint = if (isDark) MaterialTheme.colorScheme.onPrimary else Color.Black
                            )
                        }
                    }
                }
            )
        }
    }
}



