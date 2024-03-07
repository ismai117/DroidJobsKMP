import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import utils.KottieConstants

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        App()
    }
}

//@OptIn(ExperimentalComposeUiApi::class)
//fun warning(){
//
//    CanvasBasedWindow(canvasElementId = "warning") {
//
//        val composition = rememberKottieComposition(
//            spec = KottieCompositionSpec.Url("https://lottie.host/0094976a-6a83-4795-b0ce-6da075ca5b6b/HSbPWOOaJV.json")
//        )
//
//        val animationState by animateKottieCompositionAsState(
//            composition = composition,
//            iterations = KottieConstants.IterateForever
//        )
//
//        KottieAnimation(
//            composition = composition,
//            progress = { animationState.progress },
//            modifier = Modifier
//        )
//
//    }
//}