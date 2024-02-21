package navigation

import DroidJobsKMP
import JobsViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import splash.SplashScreen
import jobs.JobDetailScreen
import jobs.JobsScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel


private const val SPLASH = "splash_screen"
private const val JOBS = "jobs_screen"
private const val JOBS_DETAIL = "jobs_detail_screen"


@Composable
fun RootNavigation(
    navigator: Navigator
) {


    val jobsViewModel = viewModel(JobsViewModel::class){
        JobsViewModel(
            jobsRepository = DroidJobsKMP.jobsRepository
        )
    }


    NavHost(
        navigator = navigator,
        initialRoute = SPLASH,
        navTransition = NavTransition()
    ) {
        scene(
            route = SPLASH,
            navTransition = NavTransition()
        ) {
            SplashScreen(
                navigateToJobsScreen = {
                    navigator.navigate(JOBS)
                }
            )
        }
        scene(
            route = JOBS,
            navTransition = NavTransition()
        ) {
            val jobsState = jobsViewModel.state
            val query by jobsViewModel.query.collectAsState()
            val jobs by jobsViewModel.jobs.collectAsState()
            val searching by jobsViewModel.searching.collectAsState()
            JobsScreen(
                state = jobsState,
                query = query,
                onQueryChange = jobsViewModel::onQueryChange,
                jobs = jobs,
                searching = searching,
                navigateToJobDetailScreen = {
                    jobsViewModel.setJobID(it)
                    navigator.navigate(JOBS_DETAIL)
                }
            )
        }
        scene(
            route = JOBS_DETAIL,
            navTransition = NavTransition()
        ) {
            val jobsState = jobsViewModel.state
            JobDetailScreen(
                state = jobsState,
                getJob = {
                    jobsViewModel.getJob()
                },
                navigateBack = {
                    navigator.navigate(JOBS)
                }
            )
        }
    }


}