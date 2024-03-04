import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.AuthModule
import login.presentation.LoginScreenModel
import platform.getPlatform
import theme.LocalThemeIsDark


object SettingsScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        var theme by LocalThemeIsDark.current

        var isLight by remember { mutableStateOf(false) }
        var isDark by remember { mutableStateOf(false) }
        var isFollowingSystemTheme by remember { mutableStateOf(false) }

        val navigator = LocalNavigator.currentOrThrow
        val starterScreen = rememberScreen(Screens.StarterScreen)
        val loginScreenModel = rememberScreenModel {
            LoginScreenModel(
                loginRepository = AuthModule.loginModule.loginRepository
            )
        }

        var themeDialog by remember { mutableStateOf(false) }

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

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigator.pop()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "navigate back"
                            )
                        }
                    }
                )
            },
            modifier = modifier.padding(
                top = if (getPlatform().name == "Desktop") 24.dp else 0.dp
            )
        ) { paddingValues ->

            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {

                Column(
                    modifier = modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "General",
                        fontSize = 18.sp,
                        modifier = modifier.padding(start = 24.dp)
                    )

                    Column(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                            .height(55.dp)
                            .clickable {
                                themeDialog = true
                            },
//                           .border(width = 1.dp, color = Color.White),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Theme",
                            fontSize = 14.sp,
                            lineHeight = 1.em,
                            modifier = modifier.padding(start = 24.dp)
                        )
                        Text(
                            text = "Follow system",
                            fontSize = 14.sp,
                            lineHeight = 1.em,
                            modifier = modifier.padding(start = 24.dp)
                        )
                    }

                    HorizontalDivider()

                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .clickable {
                                loginScreenModel.logout()
                                navigator.popUntilRoot()
                                navigator.push(starterScreen)
                            },
//                           .border(width = 1.dp, color = Color.White),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Logout",
                            fontSize = 14.sp,
                            lineHeight = 1.em,
                            modifier = modifier.padding(start = 24.dp)
                        )
                    }

                }


            }

        }

        if (themeDialog) {
            Dialog(
                onDismissRequest = {
                    themeDialog = false
                }
            ) {
                ElevatedCard(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
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

}

