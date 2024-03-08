import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import splash.SplashScreen
import theme.DroidJobsKMPTheme


@Composable
fun App(
    modifier: Modifier = Modifier,
    appModule: AppModule = AppModule()
) = DroidJobsKMPTheme {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
                .navigationBarsPadding(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = modifier
                    .widthIn(max = 1270.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ){
                Navigator(SplashScreen){ navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}

