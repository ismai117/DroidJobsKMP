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
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import jobs.domain.model.JobRole
import openUrl
import theme.LocalThemeIsDark



@Composable
fun JobDetailScreen(
    jobRole: JobRole?,
    navigateBack: () -> Unit
) {

    JobDetailScreenContent(
        jobRole = jobRole,
        navigateBack = navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun JobDetailScreenContent(
    modifier: Modifier = Modifier,
    jobRole: JobRole?,
    navigateBack: () -> Unit
) {

    val scrollState = rememberScrollState()

    val isDark by LocalThemeIsDark.current

    Scaffold(
//        modifier = modifier
//            .padding(
//            top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp
//        ),
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
//                    IconButton(
//                        onClick = {
//                            if (isUserLoggedIn && jobsState.job?.id){
//                                if(!bookmarkState.bookmarks.any { it.jobID == jobsState.job?.id }){
//                                    bookmarkOnEvent(
//                                        BookmarkEvent.INSERT(
//                                            jobId = jobsState.job!!.id,
//                                            jobTitle = jobsState.job.title,
//                                            companyName = jobsState.job.company,
//                                            companyLogo = jobsState.job.companyLogo,
//                                        )
//                                    )
//                                }
//                            } else {
//                                navigateToLoginScreen()
//                            }
//                        }
//                    ) {
//                        if (isUserLoggedIn){
//                            Icon(
//                                imageVector = if (bookmarkState.bookmarks.any { it.jobID == jobsState.job?.id }) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
//                                contentDescription = "bookmark job"
//                            )
//                        }else{
//                            Icon(
//                                imageVector = Icons.Outlined.BookmarkBorder,
//                                contentDescription = "bookmark job"
//                            )
//                        }
//                    }
//                    IconButton(
//                        onClick = {
//                            scope.launch {
//                                snackbarHostState.showSnackbar("The share link feature is not yet implemented.")
//                            }
//                        }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Share,
//                            contentDescription = "share job"
//                        )
//                    }
                 }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier.padding(paddingValues)
        ) {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = jobRole?.companyLogo,
                    contentDescription = jobRole?.company.orEmpty(),
                    modifier = modifier
                        .padding(top = 40.dp)
                        .size(90.dp)
                        .clip(CircleShape)
                )

                Column(
                    modifier = modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    Text(
                        text = jobRole?.company.orEmpty(),
                        fontSize = 12.sp
                    )

                    Text(
                        text = jobRole?.companyMotto.orEmpty(),
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
                            text = jobRole?.address.orEmpty(),
                            fontSize = 12.sp
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
                            text = jobRole?.title.orEmpty(),
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
                            jobRole?.skills.orEmpty().forEach {
                                Text(
                                    text =  it,
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
                            text = jobRole?.salary.orEmpty(),
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
                            text = jobRole?.employmentType.orEmpty(),
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
                            text = jobRole?.workEnvironment.orEmpty(),
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
                            text = jobRole?.description.orEmpty(),
                            fontSize = 14.sp
                        )
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
                            openUrl(url = jobRole?.link)
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
    }

}