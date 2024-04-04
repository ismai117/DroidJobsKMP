package navigation

import AuthScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import presentation.BookmarkScreen
import presentation.JobDetailScreen
import presentation.JobsScreen
import splash.SplashScreen


private const val SPLASH = "splash_screen"
private const val AUTH = "auth_screen"
private const val JOBS = "jobs_screen"
private const val JOBS_DETAIL = "jobs_detail_screen"
private const val BOOKMARK = "bookmark_screen"
private const val SETTINGS = "settings_screen"


@Composable
fun RootNavigation(
    navController: NavController
){
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
                },
                navigateToStarterScreen = {
                    navController.navigate(AUTH)
                }
            )
        }
        composable(
            route = AUTH
        ){
            AuthScreen(
                navigateToJobsScreen = {
                    navController.navigate(JOBS)
                },
                navigateToJobDetailScreen = { jobId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("jobId", jobId)
                    navController.navigate(JOBS_DETAIL)
                }
            )
        }
        composable(
            route = JOBS
        ){
            JobsScreen(
                navigateToJobDetailScreen = { jobId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("jobId", jobId)
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
            val jobId = navController.previousBackStackEntry?.savedStateHandle?.get<String>("jobId")
            JobDetailScreen(
                id = jobId.orEmpty(),
                navigateToLoginScreen = {
                    navController.navigate(AUTH)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = BOOKMARK
        ){
            BookmarkScreen(
                navigateToJobDetailScreen = { jobId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("jobId", jobId)
                    navController.navigate(JOBS_DETAIL)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = SETTINGS
        ){
            SettingsScreen(
                navigateToBookmarkScreen = {
                    navController.navigate(BOOKMARK)
                },
                navigateToStarterScreen = {
                    navController.navigate(AUTH)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}


