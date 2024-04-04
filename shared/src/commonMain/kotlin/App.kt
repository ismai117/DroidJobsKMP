import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import di.authModule
import di.bookmarkModule
import di.jobsModule
import navigation.RootNavigation
import org.koin.compose.KoinApplication
import theme.DroidJobsKMPTheme


@Composable
fun App(
    modifier: Modifier = Modifier
) {

    KoinApplication(
        application = {
            modules(
                jobsModule,
                bookmarkModule,
                authModule
            )
        }
    ) {
        DroidJobsKMPTheme {

            val navController = rememberNavController()

            Scaffold(
                contentWindowInsets = WindowInsets(0.dp)
            ) { paddingValues ->
                Box(
                    modifier = modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = modifier
                            .widthIn(max = 1270.dp)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center,
                    ) {
                        RootNavigation(
                            navController = navController
                        )
                    }
                }
            }

        }
    }

}

