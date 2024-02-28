import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import splash.SplashScreen
import theme.DroidJobsKMPTheme


@Composable
fun App(appModule: AppModule = AppModule()) = DroidJobsKMPTheme {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Navigator(SplashScreen){ navigator ->
                SlideTransition(navigator)
            }
        }
    }
}

