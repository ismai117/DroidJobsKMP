package components


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kamel.core.getOrNull
import io.kamel.image.asyncPainterResource


@Composable
fun CompanyLogo(
    modifier: Modifier = Modifier,
    companyLogo: String,
    companyName: String
) {
    asyncPainterResource(data = companyLogo).getOrNull()?.let {
        Image(
            painter = it,
            contentDescription = "$companyName logo",
            modifier = modifier
        )
    }
}
