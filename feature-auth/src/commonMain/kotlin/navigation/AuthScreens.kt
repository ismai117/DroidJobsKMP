package navigation


sealed class AuthScreens (val route: String){
    object StarterScreen : AuthScreens(route = "starter_screen")
    object LoginScreen : AuthScreens(route = "login_screen")
    object RegisterScreen : AuthScreens(route = "register_screen")
    object ForgetPasswordScreen : AuthScreens(route = "forgetPassword_screen")
}