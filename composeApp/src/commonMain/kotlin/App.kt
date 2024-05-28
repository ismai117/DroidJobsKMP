import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import jobs.di.jobsModule
import navigation.RootNavigation
import org.koin.compose.KoinApplication
import theme.DroidJobsKMPTheme


@Composable
@Preview
fun App(
    modifier: Modifier = Modifier
) = DroidJobsKMPTheme {

    val navController = rememberNavController()

    KoinApplication(
        application = {
            modules(
                jobsModule
            )
        }
    ) {

        Scaffold {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
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

