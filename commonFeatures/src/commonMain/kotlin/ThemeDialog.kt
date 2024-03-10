import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import theme.LocalThemeIsDark

@Composable
fun ThemeDialog(
    modifier: Modifier = Modifier,
    themeDialog: Boolean,
    themeDialogOnChange: (Boolean) -> Unit
){

    var theme by LocalThemeIsDark.current

    var isLight by remember { mutableStateOf(false) }
    var isDark by remember { mutableStateOf(false) }
    var isFollowingSystemTheme by remember { mutableStateOf(false) }

    LaunchedEffect(
        key1 = theme
    ){
        if (theme){
            isLight = false
            isDark = true
        }else {
            isLight = true
            isDark = false
        }
    }

    if (themeDialog) {
        Dialog(
            onDismissRequest = {
                themeDialogOnChange(false)
            }
        ) {
            ElevatedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (theme) MaterialTheme.colorScheme.primary else Color(0xFF1C1C23)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(24.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Theme",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isLight,
                            onCheckedChange = {
                                if (!isLight){
                                    isLight = it
                                    isDark = false
                                    theme = false
                                }
                            }
                        )
                        Text(
                            text = "Light",
                            fontSize = 12.sp,
                            lineHeight = 1.em
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isDark,
                            onCheckedChange = {
                                if (!isDark){
                                    isDark = it
                                    isLight = false
                                    theme = true
                                }
                            }
                        )
                        Text(
                            text = "Dark",
                            fontSize = 12.sp,
                            lineHeight = 1.em
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isFollowingSystemTheme,
                            onCheckedChange = {
                                if (!isFollowingSystemTheme){
                                    isFollowingSystemTheme = true
                                    isLight = false
                                    isDark = false
                                }
                            }
                        )
                        Text(
                            text = "Follow system",
                            fontSize = 12.sp,
                            lineHeight = 1.em
                        )
                    }
                }
            }
        }
    }

}