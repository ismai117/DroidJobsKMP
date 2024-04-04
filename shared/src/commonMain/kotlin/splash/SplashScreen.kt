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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import user.UserModule


private typealias navigateToJobsScreen = () -> Unit
private typealias navigateToStarterScreen = () -> Unit

@Composable
fun SplashScreen(
    navigateToJobsScreen: navigateToJobsScreen,
    navigateToStarterScreen: navigateToStarterScreen
){

    SplashScreenContent(
        navigateToJobsScreen = navigateToJobsScreen,
        navigateToStarterScreen = navigateToStarterScreen
    )

}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    navigateToJobsScreen: navigateToJobsScreen,
    navigateToStarterScreen: navigateToStarterScreen
) {

    val isUserLoggedIn by UserModule.userState.isUserLoggedIn

    val composition = rememberKottieComposition(
        spec = KottieCompositionSpec.Url("https://lottie.host/0094976a-6a83-4795-b0ce-6da075ca5b6b/HSbPWOOaJV.json")
    )

    val animationState by animateKottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    LaunchedEffect(Unit){
        UserModule.userState.getUserState()
        println("isUserLoggedIn: ${UserModule.userState.isUserLoggedIn.value}")
    }

    LaunchedEffect(animationState.isPlaying){
        if (animationState.isCompleted){
            if (isUserLoggedIn){
                navigateToJobsScreen()
            }else{
                navigateToStarterScreen()
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
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }

}