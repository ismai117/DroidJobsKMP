package navigation

import DroidJobsKMP
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import splash.SplashScreen
import jobs.presentation.JobDetailScreen
import jobs.presentation.JobsScreen
import jobs.presentation.JobsViewModel

enum class NavigationItems(val route: String) {
    SPLASH("splash_screen"),
    JOBS("home_screen"),
    JOBS_DETAIL("jobs_detail_screen")
}

@Composable
fun RootNavigation() {

    var currentNavigation by remember { mutableStateOf(NavigationItems.SPLASH) }

    val scope = rememberCoroutineScope()

    val jobsViewModel = remember {
        JobsViewModel(
            coroutineScope = scope,
            jobsRepository = DroidJobsKMP.jobsRepository
        )
    }

    val jobsState = jobsViewModel.state

    when (currentNavigation) {
        NavigationItems.SPLASH -> {
            SplashScreen(
                navigateToSplashScreen = {
                     currentNavigation = NavigationItems.JOBS
                }
            )
        }

        NavigationItems.JOBS -> {
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally()
            ){
                JobsScreen(
                    state = jobsState,
                    navigateToJobDetailScreen = {
                        jobsViewModel.setJobID(it)
                        currentNavigation = NavigationItems.JOBS_DETAIL
                    }
                )
            }
        }

        NavigationItems.JOBS_DETAIL -> {
            AnimatedVisibility(
                visible = true,
                exit = slideOutHorizontally()
            ) {
                JobDetailScreen(
                    state = jobsState,
                    getJob = {
                        jobsViewModel.getJob()
                    },
                    navigateBack = {
                        currentNavigation = NavigationItems.JOBS
                    }
                )
            }
        }
    }

}