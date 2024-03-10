import cafe.adriel.voyager.core.registry.ScreenRegistry
import forgetPassword.presentation.ForgetPasswordScreen
import login.presentation.LoginScreen
import presentation.JobDetailScreen
import presentation.JobsScreen
import register.presentation.RegisterScreen
import splash.SplashScreen
import starter.presentation.StarterScreen


class AppModule {
    init {
        fun configureNavigationModule(){
            ScreenRegistry{
                register<Screens.SplashScreen> { SplashScreen }
                register<Screens.JobsScreen> { JobsScreen }
                register<Screens.JobDetailScreen> { provider -> JobDetailScreen(id = provider.id) }
                register<Screens.SettingsScreen> { SettingsScreen }
                register<Screens.BookmarkScreen> { BookmarkScreen }
                register<Screens.StarterScreen> { StarterScreen }
                register<Screens.LoginScreen> { LoginScreen }
                register<Screens.RegisterScreen> { RegisterScreen }
                register<Screens.ForgetPasswordScreen> { ForgetPasswordScreen }
            }
        }
        configureNavigationModule()
    }
}