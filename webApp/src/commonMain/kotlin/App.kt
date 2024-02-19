import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import navigation.RootNavigation
import theme.DroidJobsKMPTheme


@Composable
fun App(
    modifier: Modifier = Modifier
) = DroidJobsKMPTheme {

    Scaffold(
        containerColor = Color(0xFF1C1C23)
    ) { paddingValues ->
        Box(
            modifier = modifier.padding(paddingValues)
        ){
            RootNavigation()
        }
    }

}

