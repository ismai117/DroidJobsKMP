import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import splash.SplashScreen
import theme.DroidJobsKMPTheme


// 8f8fa6

@Composable
fun App() =  DroidJobsKMPTheme {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        containerColor = Color(0xFF1C1C23)
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Navigator(screen = SplashScreen) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}


expect fun openUrl(url: String?)