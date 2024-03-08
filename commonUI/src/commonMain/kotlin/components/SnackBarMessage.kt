package components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.LocalThemeIsDark

@Composable
fun SnackBarMessage(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        hostState = snackBarHostState
    ) { data ->
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Card(
                modifier = modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Snackbar(
                    action = {}
                ) {
                    Text(
                        text = data.visuals.message
                    )
                }
            }
        }
    }
}