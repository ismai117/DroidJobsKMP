package components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Snackbar(
                modifier = modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .align(Alignment.TopCenter),
                action = {}
            ) {
                Text(
                    text = data.visuals.message
                )
            }
        }
    }
}