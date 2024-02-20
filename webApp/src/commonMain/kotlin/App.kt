import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.RootNavigation
import theme.DroidJobsKMPTheme


@Composable
fun App(
    modifier: Modifier = Modifier
) = DroidJobsKMPTheme {

    PreComposeApp {

        val navigator = rememberNavigator()

        Scaffold(
            containerColor = Color(0xFF1C1C23)
        ) { paddingValues ->
            Box(
                modifier = modifier.padding(paddingValues)
            ){
                RootNavigation(navigator = navigator)
            }
        }
    }

}

