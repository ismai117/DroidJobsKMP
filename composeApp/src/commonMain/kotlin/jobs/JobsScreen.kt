package jobs

import DroidJobsKMP
import components.FlexLayout
import components.JobsScreenTitle
import components.SearchBarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import openUrl


object JobsScreen : Screen {


    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow

        val jobScreenModel = rememberScreenModel {
            JobsScreenModeL(
                jobsRepository = DroidJobsKMP.jobsRepository
            )
        }

        val state = jobScreenModel.state

        val query by jobScreenModel.query.collectAsState()
        val jobs by jobScreenModel.jobs.collectAsState()
        val searching by jobScreenModel.searching.collectAsState()

        // FF7966

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp)
        ) { paddingValues ->

            Box(
                modifier = modifier.padding(paddingValues)
            ) {

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFF1C1C23))
                ) {

                    JobsScreenTitle(
                        modifier = modifier.padding(
                            top = 80.dp,
                            start = 24.dp
                        )
                    )

                    SearchBarView(
                        modifier = modifier,
                        onFocused = {

                        },
                        query = query,
                        onQueryChange = jobScreenModel::onQueryChange,
                        onMic = {

                        },
                        onSort = {

                        }
                    )

                    if (state.isLoading) {
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
                            navigator.push(JobDetailScreen(it))
                        },
                        openUrl = {
                            openUrl(it)
                        }
                    )

                }


            }

        }

    }

}

