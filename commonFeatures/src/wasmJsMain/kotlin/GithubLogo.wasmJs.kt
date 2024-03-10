import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import droidjobskmp.commonfeatures.generated.resources.Res
import droidjobskmp.commonfeatures.generated.resources.icons8_github_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.LocalThemeIsDark

@OptIn(ExperimentalResourceApi::class)
@Composable
internal actual fun GithubLogo() {
    var isDark by LocalThemeIsDark.current
    Image(
        painter = painterResource(Res.drawable.icons8_github_24),
        contentDescription = "DroidJobs Repo",
        colorFilter = ColorFilter.tint(if (isDark) Color.White else Color.Black)
    )
}