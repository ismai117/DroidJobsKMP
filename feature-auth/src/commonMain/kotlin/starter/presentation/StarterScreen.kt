package starter.presentation


import Footer
import ThemeDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import autoResizeText
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.FlexLayout
import components.SnackBarMessage
import login.presentation.LoginScreen
import platform.getPlatform
import register.presentation.RegisterScreen
import jobs.JobsService
import kotlinx.coroutines.launch
import openUrl
import platform.Platforms
import theme.LocalThemeIsDark
import user.UserModule

private typealias navigateToDetailScreen = (String) -> Unit
private typealias navigateToLoginScreen = () -> Unit
private typealias navigateToRegisterScreen = () -> Unit


object StarterScreen : Screen {

    private var id by mutableStateOf("")

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val jobsDetailScreen = rememberScreen(Screens.JobDetailScreen(id))

        var containerWidth by remember { mutableStateOf(768.dp) }

        LaunchedEffect(Unit){
            UserModule.userState.getUserState()
            println("isUserLoggedIn: ${UserModule.userState.isUserLoggedIn.value}")
        }

        LaunchedEffect(id){
            if (id.isNotBlank()){
                navigator.push(jobsDetailScreen)
                id = ""
            }
        }

        StarterScreenContent(
            navigateToDetailScreen = {
                id = it
            },
            navigateToLoginScreen = {
                navigator.push(LoginScreen)
            },
            navigateToRegisterScreen = {
                navigator.push(RegisterScreen)
            },
            containerWidth = containerWidth,
            containerWidthOnChange = { containerWidth = it }
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarterScreenContent(
    modifier: Modifier = Modifier,
    navigateToDetailScreen: navigateToDetailScreen,
    navigateToLoginScreen: navigateToLoginScreen,
    navigateToRegisterScreen: navigateToRegisterScreen,
    containerWidth: Dp = 768.dp,
    containerWidthOnChange: (Dp) -> Unit
){

    var theme by LocalThemeIsDark.current

    val starterJobsService = JobsService()
    val jobs = starterJobsService.getAllJobs()

    var openDrawer by remember { mutableStateOf(false) }

    var themeDialog by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
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
        modifier = modifier.padding(
            top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp
        ).testTag("starter_topAppBar"),
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = {
            SnackBarMessage(
                snackBarHostState = snackbarHostState,
                onDismiss = {}
            )
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            containerWidthOnChange(maxWidth.value.dp)

            Column {

                FlexLayout(
                    jobs = jobs,
                    navigateToLoginScreen = {
                        navigateToLoginScreen()
                    },
                    navigateToDetailScreen = {
                        navigateToDetailScreen(it)
                    },
                    openUrl = {
                        openUrl(it)
                    },
                    shareLink = {
                        scope.launch {
                            snackbarHostState.showSnackbar("The share link feature is not yet implemented.")
                        }
                    },
                    showBanner = true
                )

            }

        }

        if (openDrawer && containerWidth < 768.dp){
            ModalBottomSheet(
                onDismissRequest = {
                    openDrawer = false
                },
                modifier = modifier.fillMaxWidth()
            ){
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
                        ){
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
                        ){
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
                        ){
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


/**
 *  fontSize = when(getPlatform().type){
 *                             Platforms.ANDROID -> {
 *                                 24.sp
 *                             }
 *                             Platforms.IOS -> {
 *                                 24.sp
 *                             }
 *                             Platforms.DESKTOP -> {
 *                                 42.sp
 *                             }
 *                             Platforms.WEB_JS -> {
 *                                 42.sp
 *                             }
 *                             Platforms.WEB_WASM -> {
 *                                 42.sp
 *                             }
 *                         },
 */