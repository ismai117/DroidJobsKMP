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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.CompanyLogo
import data.service.bulletPoint
import di.JobsModule
import platform.getPlatform


class JobDetailScreen(
    private val id: String
) : Screen {

    @OptIn(
        ExperimentalMaterial3Api::class,
        ExperimentalLayoutApi::class
    )
    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow

        val scrollState = rememberScrollState()

        val jobsViewModel = rememberScreenModel {
            JobsScreenModeL(
                jobsRepository = JobsModule.jobsRepository
            )
        }

        val state = jobsViewModel.state

        LaunchedEffect(Unit) {
            jobsViewModel.getJob(id)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigator.pop()
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
            modifier = modifier.padding(
                top = if (getPlatform().name == "Desktop") 24.dp else 0.dp
            )
        ) { paddingValues ->
            Box(
                modifier = modifier.padding(paddingValues)
            ) {

                if (!state.isLoading) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (!state.job?.companyLogo.isNullOrBlank()){
                            CompanyLogo(
                                modifier = modifier
                                    .padding(top = 40.dp)
                                    .size(90.dp)
                                    .clip(CircleShape),
                                companyLogo = state.job?.companyLogo.orEmpty(),
                                companyName = state.job?.company.orEmpty()
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
                                text = state.job?.company.orEmpty(),
                                fontSize = 12.sp
                            )

                            Text(
                                text = state.job?.title.orEmpty(),
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
                                    text = "${if (state.job?.address?.isNotBlank() == true) state.job.address else state.job?.city}",
                                    fontSize = 12.sp
                                )
                            }

                            Text(
                                text = state.job?.industry.orEmpty(),
                                modifier = modifier.padding(top = 20.dp),
                                fontSize = 12.sp
                            )

                            Text(
                                text = state.job?.companyMotto.orEmpty(),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = modifier
                                    .padding(top = 20.dp, start = 24.dp, end = 24.dp)
                            )

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
                                    state.job?.skills.orEmpty().forEach {
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
                                    state.job?.skills.orEmpty().forEach {
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
                                    text = state.job?.salary.orEmpty(),
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
                                    text = state.job?.employmentType.orEmpty(),
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
                                    text = state.job?.workEnvironment.orEmpty(),
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
                                    text = state.job?.visaSponsorship.orEmpty(),
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
                                    text = state.job?.description.orEmpty(),
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
                                state.job?.requirements.orEmpty().lines()
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
                                    text = state.job?.aboutUs.orEmpty(),
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
                                state.job?.companyBenefits.orEmpty().lines()
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
                                    openUrl(url = state.job?.link)
                                },
                                modifier = modifier
                                    .width(120.dp)
                                    .height(30.dp),
                                shape = RectangleShape,
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "APPLY"
                                )
                            }

                        }

                    }
                }

                if (state.isLoading) {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

            }
        }


    }

}
