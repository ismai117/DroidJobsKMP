package jobs

import components.FlexLayout
import components.JobsScreenTitle
import components.SearchBarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.Jobs
import utils.openUrl


private typealias navigateToJobDetailScreen = (String) -> Unit


expect fun onMic()
expect fun onFocused()
expect fun onSort()


@Composable
fun JobsScreen(
    modifier: Modifier = Modifier,
    state: JobsState,
    query: String,
    onQueryChange: (String) -> Unit,
    jobs: List<Jobs>,
    searching:Boolean,
    navigateToJobDetailScreen: navigateToJobDetailScreen
) {
    // FF7966

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->

        Box(
            modifier = modifier.padding(paddingValues)
        ) {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFF1C1C23))
            ) {

                JobsScreenTitle(
                    modifier = modifier.padding(
                        top = 40.dp,
                        start = 24.dp
                    )
                )

                SearchBarView(
                    modifier = modifier,
                    onFocused = {
                        onFocused()
                    },
                    query = query,
                    onQueryChange = onQueryChange,
                    onMic = {
                        onMic()
                    },
                    onSort = {
                        onSort()
                    }
                )

                if (state.isLoading) {
                    Box(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if (searching) {
                    Box(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }

                FlexLayout(
                    jobs = jobs,
                    navigateToDetailScreen = {
                        navigateToJobDetailScreen(it)
                    },
                    openUrl = {
                        openUrl(it)
                    }
                )

            }

        }

    }

}