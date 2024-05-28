package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jobs.domain.model.JobRole
import presentation.JobDetailScreen
import jobs.presentation.JobsScreen
import settings.SettingsScreen
import splash.SplashScreen


private const val SPLASH = "splash_screen"
private const val JOBS = "jobs_screen"
private const val JOBS_DETAIL = "jobs_detail_screen"
private const val SETTINGS = "settings_screen"


@Composable
fun RootNavigation(
    navController: NavController
){

    var jobRole by remember { mutableStateOf<JobRole?>(null) }

    NavHost(
        navController = navController as NavHostController,
        startDestination = SPLASH
    ){
        composable(
            route = SPLASH
        ){
            SplashScreen(
                navigateToJobsScreen = {
                    navController.navigate(JOBS)
                }
            )
        }
        composable(
            route = JOBS
        ){
            JobsScreen(
                navigateToJobDetailScreen = { role ->
                    jobRole = role
                    navController.navigate(JOBS_DETAIL)
                },
                navigateToSettingsScreen = {
                    navController.navigate(SETTINGS)
                }
            )
        }
        composable(
            route = JOBS_DETAIL
        ){
            JobDetailScreen(
                jobRole = jobRole,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = SETTINGS
        ){
            SettingsScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}


