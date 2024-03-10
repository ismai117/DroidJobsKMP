import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.AuthModule
import login.presentation.LoginScreenModel
import platform.getPlatform
import bookmark.presentation.BookmarkEvent
import components.ProgressBar
import di.BookmarkModule
import bookmark.presentation.BookmarkScreenModel
import components.CompanyLogo
import presentation.BookmarkState
import starter.presentation.StarterScreen


object BookmarkScreen : Screen {

    private var id by mutableStateOf("")

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val jobsDetailScreen = rememberScreen(Screens.JobDetailScreen(id))

        val loginScreenModel = rememberScreenModel {
            LoginScreenModel(
                loginRepository = AuthModule.loginModule.loginRepository
            )
        }

        val bookmarkScreenModel = rememberScreenModel {
            BookmarkScreenModel(
                bookmarkRepository = BookmarkModule.bookmarkRepository
            )
        }

        val bookmarkState = bookmarkScreenModel.state


        LaunchedEffect(id) {
            if (id.isNotBlank()) {
                navigator.push(jobsDetailScreen)
                id = ""
            }
        }


        BookmarkScreenContent(
            state = bookmarkState,
            onEvent = {

            },
            navigateToDetailScreen = {
                id = it
            },
            navigateBack = {
                navigator.pop()
            }
        )

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreenContent(
    modifier: Modifier = Modifier,
    state: BookmarkState,
    onEvent: (BookmarkEvent) -> Unit,
    navigateToDetailScreen: (String) -> Unit,
    navigateBack: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bookmarks"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "navigate back"
                        )
                    }
                }
            )
        },
        modifier = modifier.padding(
            top = if (getPlatform().name == "Desktop") 24.dp else 0.dp
        )
    ) { paddingValues ->

        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {


            LazyColumn(
                modifier = modifier.padding(top = 24.dp),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {

                items(
                    items = state.bookmarks,
                    key = { it._id }
                ) { item ->

                    OutlinedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable {
                                navigateToDetailScreen(item.jobID)
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.outlinedCardColors()
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            CompanyLogo(
                                modifier = modifier
                                    .padding(start = 12.dp)
                                    .size(50.dp)
                                    .clip(CircleShape),
                                companyLogo = item.companyLogo,
                                companyName = item.companyName
                            )
                            Column(
                                modifier = modifier
                                    .padding(start = 12.dp),
//                                        .border(width = 1.dp, color = Color.White),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = item.companyName,
                                    fontSize = 12.sp,
                                    modifier = modifier,
//                                            .border(width = 1.dp, color = Color.White),
                                    lineHeight = 1.em
                                )
                                Text(
                                    text = item.jobTitle,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
//                                        modifier = modifier.border(width = 1.dp, color = Color.White)
                                )
                            }
                        }
                    }

                }

            }

            ProgressBar(isLoading = state.isFindBookmarksLoading)

        }

    }

}