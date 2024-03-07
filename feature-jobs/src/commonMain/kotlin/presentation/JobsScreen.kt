package presentation


import components.FlexLayout
import components.SearchBarView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.JobsModule
import domain.model.Jobs
import platform.getPlatform



object JobsScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val settingsScreen = rememberScreen(Screens.SettingsScreen)

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
            jobScreenModel.getAllJobs()
        }

        JobsScreenContent(
            jobsState = jobsState,
            jobs = jobs,
            query = query,
            onQueryChange = jobScreenModel::onQueryChange,
            searching = searching,
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
    navigateToDetailScreen: (String) -> Unit,
    navigateToSettingsScreen: () -> Unit
){


    Scaffold(
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
        modifier = modifier.padding(
            top = if (getPlatform().name == "Desktop") 24.dp else 0.dp
        )
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
                    navigateToDetailScreen = {
                        navigateToDetailScreen(it)
                    },
                    openUrl = {
                        openUrl(it)
                    }
                )

            }


        }

    }


}


expect fun openUrl(url: String?)
