import cafe.adriel.voyager.core.registry.ScreenRegistry
import forgetPassword.ForgetPasswordScreen
import login.presentation.LoginScreen
import presentation.JobDetailScreen
import presentation.JobsScreen
import register.presentation.RegisterScreen


class AppModule {
    init {
        fun configureNavigationModule(){
            ScreenRegistry{
                register<Screens.JobsScreen> { JobsScreen }
                register<Screens.JobDetailScreen> { provider -> JobDetailScreen(id = provider.id) }
                register<Screens.LoginScreen> { LoginScreen }
                register<Screens.SettingsScreen> { SettingsScreen }
                register<Screens.RegisterScreen> { RegisterScreen }
                register<Screens.ForgetPasswordScreen> { ForgetPasswordScreen }
            }
        }
        configureNavigationModule()
    }
}