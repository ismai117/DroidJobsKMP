package presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import autoResizeText
import bookmark.presentation.BookmarkEvent
import components.CompanyLogo
import components.SnackBarMessage
import jobs.bulletPoint
import kotlinx.coroutines.launch
import openUrl
import org.koin.compose.koinInject
import platform.Platforms
import platform.getPlatform
import theme.LocalThemeIsDark
import user.UserModule

private typealias navigateToLoginScreen = () -> Unit
private typealias navigateBack = () -> Unit

@Composable
fun JobDetailScreen(
    id: String,
    navigateToLoginScreen: navigateToLoginScreen,
    navigateBack: navigateBack
) {

    val isUserLoggedIn by UserModule.userState.isUserLoggedIn

    val jobsViewModeL = koinInject<JobsViewModel>()

    val jobsState = jobsViewModeL.state

    val bookmarkViewModel= koinInject<BookmarkViewModel>()
    val bookmarkState = bookmarkViewModel.state

    LaunchedEffect(Unit) {
        jobsViewModeL.getJob(id)
    }

    LaunchedEffect(Unit){
        UserModule.userState.getUserState()
    }

    JobDetailScreenContent(
        jobsState = jobsState,
        isUserLoggedIn = isUserLoggedIn,
        bookmarkState = bookmarkState,
        bookmarkOnEvent = {
            bookmarkViewModel.onEvent(it)
        },
        refreshBookmarks = {
            bookmarkViewModel.getBookmarks()
        },
        navigateToLoginScreen = navigateToLoginScreen,
        navigateBack = navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun JobDetailScreenContent(
    modifier: Modifier = Modifier,
    jobsState: JobsState,
    isUserLoggedIn: Boolean,
    bookmarkState: BookmarkState,
    bookmarkOnEvent: (BookmarkEvent) -> Unit,
    refreshBookmarks: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    navigateBack: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    val isDark by LocalThemeIsDark.current

    LaunchedEffect(bookmarkState.statusInsertBookmark){
        if (bookmarkState.statusInsertBookmark){
            refreshBookmarks()
            snackbarHostState.showSnackbar("Job added to your bookmark!")
        }
    }

    Scaffold(
        modifier = modifier
            .padding(
            top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp
        ),
        topBar = {
            TopAppBar(
                modifier = modifier
                    .statusBarsPadding(),
                title = {},
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
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (isUserLoggedIn && !jobsState.job?.id.isNullOrBlank()){
                                if(!bookmarkState.bookmarks.any { it.jobID == jobsState.job?.id }){
                                    bookmarkOnEvent(
                                        BookmarkEvent.INSERT(
                                            jobId = jobsState.job!!.id,
                                            jobTitle = jobsState.job.title,
                                            companyName = jobsState.job.company,
                                            companyLogo = jobsState.job.companyLogo,
                                        )
                                    )
                                }
                            } else {
                                navigateToLoginScreen()
                            }
                        }
                    ) {
                        if (isUserLoggedIn){
                            Icon(
                                imageVector = if (bookmarkState.bookmarks.any { it.jobID == jobsState.job?.id }) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                contentDescription = "bookmark job"
                            )
                        }else{
                            Icon(
                                imageVector = Icons.Outlined.BookmarkBorder,
                                contentDescription = "bookmark job"
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("The share link feature is not yet implemented.")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "share job"
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackBarMessage(
                snackBarHostState = snackbarHostState,
                onDismiss = {}
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier.padding(paddingValues)
        ) {

            if (!jobsState.isLoading) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (!jobsState.job?.companyLogo.isNullOrBlank()){
                        CompanyLogo(
                            modifier = modifier
                                .padding(top = 40.dp)
                                .size(90.dp)
                                .clip(CircleShape),
                            companyLogo = jobsState.job?.companyLogo.orEmpty(),
                            companyName = jobsState.job?.company.orEmpty()
                        )
                    }

                    Column(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {

                        Text(
                            text = jobsState.job?.company.orEmpty(),
                            fontSize = 12.sp
                        )

                        Text(
                            text = jobsState.job?.title.orEmpty(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Row(
                            modifier = modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "location",
                            )
                            Text(
                                text = "${if (jobsState.job?.address?.isNotBlank() == true) jobsState.job.address else jobsState.job?.city}",
                                fontSize = 12.sp
                            )
                        }

                        Text(
                            text = jobsState.job?.industry.orEmpty(),
                            modifier = modifier.padding(top = 20.dp),
                            fontSize = 12.sp
                        )

                        Box(
                            modifier = modifier.padding(top = 20.dp, start = 24.dp, end = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            autoResizeText(
                                text = jobsState.job?.companyMotto.orEmpty(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Overview",
                            fontSize = 24.sp
                        )
                        HorizontalDivider(
                            modifier = modifier
                                .padding(top = 12.dp)
                                .width(120.dp),
                            thickness = 3.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = "Skills & Experience",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Job roles:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Mobile Developer",
                                fontSize = 14.sp
                            )
                        }

                        Column(
                            modifier = modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Tech stack/tooling used:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            FlowRow {
                                jobsState.job?.skills.orEmpty().forEach {
                                    Text(
                                        text = it,
                                        fontSize = 14.sp,
                                        modifier = modifier.padding(end = 8.dp)
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Core skills we consider:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            FlowRow {
                                jobsState.job?.skills.orEmpty().forEach {
                                    Text(
                                        text = it,
                                        fontSize = 14.sp,
                                        modifier = modifier.padding(end = 8.dp)
                                    )
                                }
                            }
                        }

                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = "Logistics",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Base Salary:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = jobsState.job?.salary.orEmpty(),
                                fontSize = 14.sp
                            )
                        }

                        Column(
                            modifier = modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Employment type:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = jobsState.job?.employmentType.orEmpty(),
                                fontSize = 14.sp
                            )
                        }

                        Column(
                            modifier = modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Remote working:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = jobsState.job?.workEnvironment.orEmpty(),
                                fontSize = 14.sp
                            )
                        }

                        Column(
                            modifier = modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Visa sponsorship:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = jobsState.job?.visaSponsorship.orEmpty(),
                                fontSize = 14.sp
                            )
                        }

                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = "Job Description",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = jobsState.job?.description.orEmpty(),
                                fontSize = 14.sp
                            )
                        }

                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = "Requirements",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            jobsState.job?.requirements.orEmpty().lines()
                                .forEachIndexed { _, requirement ->
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        if (!requirement.endsWith(":") && requirement.isNotBlank()) {
                                            Text(
                                                text = bulletPoint
                                            )
                                        }
                                        Text(
                                            text = requirement,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                        }

                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = "About Us",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = jobsState.job?.aboutUs.orEmpty(),
                                fontSize = 14.sp
                            )
                        }

                    }

                    Column(
                        modifier = modifier
                            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = "Company Benefits",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            jobsState.job?.companyBenefits.orEmpty().lines()
                                .forEachIndexed { _, benefit ->
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        if (!benefit.endsWith(":") && benefit.isNotBlank()) {
                                            Text(
                                                text = bulletPoint
                                            )
                                        }
                                        Text(
                                            text = benefit,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                        }

                    }

                    Box(
                        modifier = modifier
                            .padding(top = 40.dp, bottom = 40.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {

                        Button(
                            onClick = {
                                openUrl(url = jobsState.job?.link)
                            },
                            modifier = modifier
                                .width(120.dp)
                                .height(30.dp),
                            shape = RectangleShape,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "APPLY",
                                color = if (isDark) Color.White else Color.Black
                            )
                        }

                    }

                }
            }

            if (jobsState.isLoading) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

        }
    }

    DisposableEffect(Unit){
        onDispose {
            bookmarkOnEvent(
                BookmarkEvent.CLEAR
            )
        }
    }


}