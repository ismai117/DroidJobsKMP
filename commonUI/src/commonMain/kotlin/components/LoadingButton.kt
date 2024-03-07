package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.LocalThemeIsDark


@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    isLoading: Boolean
){
    val isDark by LocalThemeIsDark.current
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        if (isLoading){
            CircularProgressIndicator(
                modifier = modifier.size(24.dp),
                color = if (isDark) Color.White else MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = label
            )
        }
    }
}