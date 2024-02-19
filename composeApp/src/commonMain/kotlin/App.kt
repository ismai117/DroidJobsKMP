import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import splash.SplashScreen
import theme.DroidJobsKMPTheme



@Composable
fun App() =  DroidJobsKMPTheme {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C23))
    ) {
        Navigator(SplashScreen)
    }
}


expect fun openUrl(url: String?)