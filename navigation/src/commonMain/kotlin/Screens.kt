import cafe.adriel.voyager.core.registry.ScreenProvider


sealed class Screens : ScreenProvider {
    data object SplashScreen : Screens()
    data object JobsScreen : Screens()
    data class JobDetailScreen(val id: String) : Screens()
    data object SettingsScreen : Screens()
    data object StarterScreen : Screens()
    data object LoginScreen : Screens()
    data object RegisterScreen : Screens()
    data object ForgetPasswordScreen : Screens()
}