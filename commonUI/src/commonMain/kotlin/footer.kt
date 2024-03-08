import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import droidjobskmp.commonui.generated.resources.Res
import droidjobskmp.commonui.generated.resources.icons8_github_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.LocalThemeIsDark


@OptIn(ExperimentalResourceApi::class)
@Composable
fun Footer(
    modifier: Modifier = Modifier
){

    val isDark by LocalThemeIsDark.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
//                    .border(width = 1.dp, color = Color.White),
        contentAlignment = Alignment.Center
    ){
        TextButton(
            onClick = {
                openUrl("https://github.com/ismai117/DroidJobsKMP")
            }
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.icons8_github_24),
                    contentDescription = "DroidJobs Repo",
                    colorFilter = ColorFilter.tint(if (isDark) Color.White else Color.Black)
                )
                Text(
                    text = "DroidJobs Repository",
                    lineHeight = 1.em
                )
            }
        }
    }
}