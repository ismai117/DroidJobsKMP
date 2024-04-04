package presentation


import components.SearchBarView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.JobsFlexLayout
import components.SnackBarMessage
import components.isScrollingUp
import jobs.Jobs
import kotlinx.coroutines.launch
import openUrl
import org.koin.compose.koinInject
import platform.Platforms
import platform.getPlatform
import user.UserModule


private typealias navigateToJobDetailScreen = (String) -> Unit
private typealias navigateToSettingsScreen = () -> Unit

@Composable
fun JobsScreen(
    navigateToJobDetailScreen: (String) -> Unit,
    navigateToSettingsScreen: () -> Unit
) {

    val jobsViewModel = koinInject<JobsViewModel>()

    val jobsState = jobsViewModel.state

    val query by jobsViewModel.query.collectAsState()
    val jobs by jobsViewModel.jobs.collectAsState()
    val searching by jobsViewModel.searching.collectAsState()


    LaunchedEffect(Unit) {
        UserModule.userState.getUserState()
    }

    LaunchedEffect(Unit) {
        jobsViewModel.getAllJobs()
    }

    JobsScreenContent(
        jobsState = jobsState,
        jobs = jobs,
        query = query,
        onQueryChange = jobsViewModel::onQueryChange,
        searching = searching,
        navigateToJobDetailScreen = navigateToJobDetailScreen,
        navigateToSettingsScreen = navigateToSettingsScreen
    )

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
    navigateToJobDetailScreen: (String) -> Unit,
    navigateToSettingsScreen: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val lazyGridState = rememberLazyGridState()

    val isScrolling = lazyGridState.isScrollingUp()

    LaunchedEffect(isScrolling) {
        println("is scrolling")
    }

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
                    ) {
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
        },
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

                JobsFlexLayout(
                    modifier = modifier,
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
                    navigateToDetailScreen = {
                        navigateToJobDetailScreen(it)
                    }
                )

            }


        }

    }


}


