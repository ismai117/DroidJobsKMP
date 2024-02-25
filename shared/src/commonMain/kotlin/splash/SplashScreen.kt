package splash

import KottieAnimation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import login.presentation.LoginScreen
import presentation.JobsScreen
import user.UserModule


object SplashScreen : Screen {

    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow
        val jobsScreen = rememberScreen(Screens.JobsScreen)
        val loginScreen = rememberScreen(Screens.LoginScreen)

        val isUserLoggedIn by UserModule.userState.isUserLoggedIn.collectAsState()

        val composition = rememberKottieComposition(
            spec = KottieCompositionSpec.Url("https://lottie.host/0094976a-6a83-4795-b0ce-6da075ca5b6b/HSbPWOOaJV.json")
        )

        val animationState by animateKottieCompositionAsState(
            composition = composition,
            iterations = 1
        )

        LaunchedEffect(animationState.isPlaying){
            if (animationState.isCompleted){
                if (isUserLoggedIn){
                    navigator.push(jobsScreen)
                }else{
                    navigator.push(loginScreen)
                }
            }
        }

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = modifier
                        .size(200.dp),
                    contentAlignment = Alignment.Center
                ){
                    KottieAnimation(
                        composition = composition,
                        progress = { animationState.progress },
                        modifier = modifier.fillMaxSize(),
                        backgroundColor = MaterialTheme.colorScheme.background
                    )
                }

                Text(
                    text = "DroidJobs",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }

}