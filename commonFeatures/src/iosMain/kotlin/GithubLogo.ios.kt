import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import compose.icons.FeatherIcons
import compose.icons.feathericons.Github
import theme.LocalThemeIsDark

@Composable
internal actual fun GithubLogo() {
    val isDark by LocalThemeIsDark.current
    Icon(
        imageVector = FeatherIcons.Github,
        contentDescription = "DroidJobs Repo",
        tint = if (isDark) Color.White else Color.Black
    )
}