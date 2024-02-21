package jobs

import FlexLayout
import JobsScreenTitle
import JobsViewModel
import SearchBarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Jobs
import utils.openUrl


private typealias navigateToJobDetailScreen = (String) -> Unit

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
                    isWeb = true,
                    query = query,
                    onQueryChange = onQueryChange
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