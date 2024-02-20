import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun JobsScreenTitle(){
    Text(
        text = "Jobs",
        color = Color.White,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(
                top = 80.dp,
                start = 24.dp
            )
    )
}