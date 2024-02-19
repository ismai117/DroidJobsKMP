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
import splash.SplashScreen
import theme.DroidJobsKMPTheme



@Composable
fun App() =  DroidJobsKMPTheme {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFF1C1C23))
        ) {
            Navigator(
                SplashScreen
            )
        }
    }
}


expect fun openUrl(url: String?)