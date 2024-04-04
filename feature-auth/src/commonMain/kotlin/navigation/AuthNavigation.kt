package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import forgetPassword.presentation.ForgetPasswordScreen
import forgetPassword.presentation.ForgetPasswordViewModel
import login.presentation.LoginScreen
import login.presentation.LoginViewModel
import org.koin.compose.koinInject
import register.presentation.RegisterScreen
import register.presentation.RegisterViewModel
import starter.presentation.StarterScreen

private typealias navigateToJobsScreen = () -> Unit
private typealias navigateToJobDetailScreen = (String) -> Unit

@Composable
fun AuthNavigation(
    navController: NavController,
    navigateToJobsScreen: navigateToJobsScreen,
    navigateToJobDetailScreen: navigateToJobDetailScreen,
    loginViewModel: LoginViewModel = koinInject(),
    registerViewModel: RegisterViewModel = koinInject(),
    forgetPasswordViewModel: ForgetPasswordViewModel = koinInject()
){

    val loginState = loginViewModel.state

    val registerState = registerViewModel.state

    val forgetPasswordState = forgetPasswordViewModel.state

    NavHost(
        navController = navController as NavHostController,
        startDestination = AuthScreens.StarterScreen.route
    ){
        composable(
            route = AuthScreens.StarterScreen.route
        ){
            StarterScreen(
                navigateToJobDetailScreen = navigateToJobDetailScreen,
                navigateToLoginScreen = {
                    navController.navigate(AuthScreens.LoginScreen.route)
                },
                navigateToRegisterScreen = {
                    navController.navigate(AuthScreens.RegisterScreen.route)
                }
            )
        }
        composable(
            route = AuthScreens.LoginScreen.route
        ){
            LoginScreen(
                loginState = loginState,
                onEvent = loginViewModel::onEvent,
                navigateToJobsScreen = navigateToJobsScreen,
                navigateToRegisterScreen = {
                    navController.navigate(AuthScreens.RegisterScreen.route)
                },
                navigateToForgetPasswordScreen = {
                    navController.navigate(AuthScreens.ForgetPasswordScreen.route)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AuthScreens.RegisterScreen.route
        ){
            RegisterScreen(
                registerState = registerState,
                onEvent = registerViewModel::onEvent,
                navigateToJobsScreen = navigateToJobsScreen,
                navigateToLoginScreen = {
                    navController.navigate(AuthScreens.LoginScreen.route)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AuthScreens.ForgetPasswordScreen.route
        ){
            ForgetPasswordScreen(
                forgetPasswordState = forgetPasswordState,
                onEvent = forgetPasswordViewModel::onEvent,
                navigateToLoginScreen = {
                    navController.navigate(AuthScreens.LoginScreen.route)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}