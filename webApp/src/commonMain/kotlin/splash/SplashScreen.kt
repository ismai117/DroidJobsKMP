package splash

import KottieAnimation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition



private typealias  navigateToJobsScreen = () -> Unit

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToJobsScreen: navigateToJobsScreen
){

    val composition = rememberKottieComposition(
        spec = KottieCompositionSpec.Url("https://lottie.host/0094976a-6a83-4795-b0ce-6da075ca5b6b/HSbPWOOaJV.json")
    )

    val animationState by animateKottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    LaunchedEffect(animationState.isPlaying){
        if (animationState.isCompleted){
            navigateToJobsScreen()
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
                    backgroundColor = Color(0xFF1C1C23)
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