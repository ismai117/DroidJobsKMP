package presentation

import Screens
import androidx.compose.foundation.border
import components.FlexLayout
import components.SearchBarView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bookmark.presentation.BookmarkScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.SnackBarMessage
import di.BookmarkModule
import di.JobsModule
import jobs.Jobs
import kotlinx.coroutines.launch
import openUrl
import platform.Platforms
import platform.getPlatform
import user.UserModule


object JobsScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val settingsScreen = rememberScreen(Screens.SettingsScreen)
        val loginScreen = rememberScreen(Screens.LoginScreen)

        val jobScreenModel = rememberScreenModel {
            JobsScreenModeL(
                jobsRepository = JobsModule.jobsRepository
            )
        }

        val jobsState = jobScreenModel.state

        val query by jobScreenModel.query.collectAsState()
        val jobs by jobScreenModel.jobs.collectAsState()
        val searching by jobScreenModel.searching.collectAsState()


        LaunchedEffect(Unit){
            UserModule.userState.getUserState()
        }

        LaunchedEffect(Unit){
            jobScreenModel.getAllJobs()
        }

        JobsScreenContent(
            jobsState = jobsState,
            jobs = jobs,
            query = query,
            onQueryChange = jobScreenModel::onQueryChange,
            searching = searching,
            navigateToLoginScreen = {
                navigator.push(loginScreen)
            },
            navigateToDetailScreen = {
                navigator.push(JobDetailScreen(it))
            },
            navigateToSettingsScreen = {
                navigator.push(settingsScreen)
            }
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreenContent(
    modifier: Modifier = Modifier,
    jobsState: JobsState,
    jobs: List<Jobs>,
    query: String,
    onQueryChange: (String) -> Unit,
    searching: Boolean,
    navigateToLoginScreen: () -> Unit,
    navigateToDetailScreen: (String) -> Unit,
    navigateToSettingsScreen: () -> Unit
){

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier
            .padding(
            top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp
        ),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Jobs",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            navigateToSettingsScreen()
                        }
                    ){
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackBarMessage(
                snackBarHostState = snackbarHostState,
                onDismiss = {}
            )
        }
    ) { paddingValues ->

        Box(
            modifier = modifier.padding(paddingValues)
        ) {

            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {

                SearchBarView(
                    query = query,
                    onQueryChange = onQueryChange,
                    onMic = {

                    },
                    onSort = {

                    }
                )

                if (jobsState.isLoading) {
                    Box(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if (searching) {
                    Box(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }

                FlexLayout(
                    modifier = modifier,
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
                    }
                )

            }


        }

    }


}


