import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import navigation.AuthNavigation


private typealias navigateToJobsScreen = () -> Unit
private typealias navigateToJobDetailScreen = (String) -> Unit

@Composable
fun AuthScreen(
    navigateToJobsScreen: navigateToJobsScreen,
    navigateToJobDetailScreen: navigateToJobDetailScreen,
){

    val navController = rememberNavController()

    AuthScreenContent(
        navController = navController,
        navigateToJobsScreen = navigateToJobsScreen,
        navigateToJobDetailScreen = navigateToJobDetailScreen
    )

}

@Composable
fun AuthScreenContent(
    navController: NavController,
    navigateToJobsScreen: navigateToJobsScreen,
    navigateToJobDetailScreen: navigateToJobDetailScreen,
){

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ){
            AuthNavigation(
                navController = navController,
                navigateToJobsScreen = navigateToJobsScreen,
                navigateToJobDetailScreen = navigateToJobDetailScreen
            )
        }
    }

}