package starter.presentation


import ThemeDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import components.SnackBarMessage
import jobs.JobsService
import kotlinx.coroutines.launch
import openUrl
import platform.Platforms
import platform.getPlatform
import starter.components.StarterFlexLayout
import starter.components.isScrollingUp
import theme.LocalThemeIsDark
import user.UserModule


private typealias navigateToJobDetailScreen = (String) -> Unit
private typealias navigateToLoginScreen = () -> Unit
private typealias navigateToRegisterScreen = () -> Unit


@Composable
fun StarterScreen (
     navigateToJobDetailScreen: navigateToJobDetailScreen,
     navigateToLoginScreen: navigateToLoginScreen,
     navigateToRegisterScreen: navigateToRegisterScreen
) {

    var containerWidth by remember { mutableStateOf(768.dp) }

    LaunchedEffect(Unit) {
        UserModule.userState.getUserState()
    }

    StarterScreenContent(
        containerWidth = containerWidth,
        containerWidthOnChange = {
            containerWidth = it
        },
        navigateToJobDetailScreen = navigateToJobDetailScreen,
        navigateToLoginScreen = navigateToLoginScreen,
        navigateToRegisterScreen = navigateToRegisterScreen
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarterScreenContent(
    modifier: Modifier = Modifier,
    containerWidth: Dp = 768.dp,
    containerWidthOnChange: (Dp) -> Unit,
    navigateToJobDetailScreen: navigateToJobDetailScreen,
    navigateToLoginScreen: navigateToLoginScreen,
    navigateToRegisterScreen: navigateToRegisterScreen
) {

    var theme by LocalThemeIsDark.current

    val starterJobsService = JobsService()
    val jobs = starterJobsService.getAllJobs()

    var openDrawer by remember { mutableStateOf(false) }

    var themeDialog by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val lazyGridState = rememberLazyGridState()
    val isScrolling = lazyGridState.isScrollingUp()

    LaunchedEffect(isScrolling) {
        if (isScrolling) {
            println("is scrolling up")
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier
                    .statusBarsPadding()
                    .padding(top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp).testTag("starter_topAppBar"),
//                    .border(width = 1.dp, color = Color.White),
                title = {
                    Text(
                        text = "DroidJobs",
                        lineHeight = 1.em,
                        modifier = modifier
                            .testTag("starter_topAppBar_title")
                    )
                },
                actions = {
                    if (containerWidth < 768.dp) {
                        IconButton(
                            onClick = {
                                openDrawer = true
                            },
                            modifier = modifier
                                .testTag("starter_topAppBar_menu_icon")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = ""
                            )
                        }
                    } else {
                        TextButton(
                            onClick = {
                                navigateToLoginScreen()
                            },
                            modifier = modifier
                                .testTag("starter_topAppBar_signIn_textBtn")
                        ) {
                            Text(
                                text = "Sign in",
                                lineHeight = 1.em
                            )
                        }
                        TextButton(
                            onClick = {
                                navigateToRegisterScreen()
                            },
                            modifier = modifier
                                .testTag("starter_topAppBar_signUp_textBtn")
                        ) {
                            Text(
                                text = "Sign up",
                                lineHeight = 1.em
                            )
                        }
                        IconButton(
                            onClick = { theme = !theme },
                            modifier = modifier
                                .testTag("starter_topAppBar_theme_icon")
                        ) {
                            Icon(
                                imageVector = if (theme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        snackbarHost = {
            SnackBarMessage(
                snackBarHostState = snackbarHostState,
                onDismiss = {}
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
    ) { paddingValues ->

        BoxWithConstraints(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            containerWidthOnChange(maxWidth.value.dp)

            StarterFlexLayout(
                lazyGridState = lazyGridState,
                jobs = jobs,

                openUrl = {
                    openUrl(it)
                },
                shareLink = {
                    scope.launch {
                        snackbarHostState.showSnackbar("The share link feature is not yet implemented.")
                    }
                },
                navigateToDetailScreen = navigateToJobDetailScreen
            )

        }

        if (openDrawer && containerWidth < 768.dp) {
            ModalBottomSheet(
                onDismissRequest = {
                    openDrawer = false
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier.fillMaxSize()
                ) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .clickable {
                                openDrawer = false
                                navigateToLoginScreen()
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Sign in",
                                modifier = modifier.padding(start = 16.dp),
                                lineHeight = 1.em
                            )
                        }
                    }
                    HorizontalDivider()
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .clickable {
                                openDrawer = false
                                navigateToRegisterScreen()
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Sign up",
                                modifier = modifier.padding(start = 16.dp),
                                lineHeight = 1.em
                            )
                        }
                    }
                    HorizontalDivider()
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .clickable {
                                themeDialog = true
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Theme",
                                modifier = modifier.padding(start = 16.dp),
                                lineHeight = 1.em
                            )
                        }
                    }
                }
            }
        } else {
            openDrawer = false
        }

    }

    ThemeDialog(
        themeDialog = themeDialog,
        themeDialogOnChange = {
            themeDialog = it
        }
    )

}

