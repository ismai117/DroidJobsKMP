package components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun JobsScreenTitle (
    modifier: Modifier = Modifier
) {

    Box {
        Text(
            text = "JobRole",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.align(Alignment.CenterStart)
        )
//        IconButton(
//            onClick = { isDark = !isDark },
//            modifier = modifier
//                .padding(end = 16.dp)
//                .size(24.dp)
//        ) {
//            Icon(
//                imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
//                contentDescription = null
//            )
//        }
    }
}