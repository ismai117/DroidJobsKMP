package jobs

import DroidJobsKMP
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.service.bulletPoint
import getPlatform
import openUrl
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


class JobDetailScreen(
    private val id: String
) : Screen {

    @OptIn(
        ExperimentalMaterial3Api::class,
        ExperimentalResourceApi::class,
        ExperimentalLayoutApi::class
    )
    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow

        val scrollState = rememberScrollState()

        val jobsViewModel = rememberScreenModel {
            JobsScreenModeL(
                jobsRepository = DroidJobsKMP.jobsRepository
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
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF1C1C23),
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            },
            containerColor = Color(0xFF1C1C23),
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
                            .verticalScroll(scrollState)
                            .background(
                                Color(0xFF1C1C23)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (!state.job?.companyLogo.isNullOrBlank()){
                            Image(
                                painter = painterResource(DrawableResource("drawable/"+state.job?.companyLogo)),
                                contentDescription = "${state.job?.company} icon",
                                modifier = modifier
                                    .padding(top = 40.dp)
                                    .size(90.dp)
                                    .clip(CircleShape)
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
                                color = Color.White,
                                fontSize = 12.sp
                            )

                            Text(
                                text = state.job?.title.orEmpty(),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
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
                                    tint = Color.White
                                )
                                Text(
                                    text = "${if (state.job?.address?.isNotBlank() == true) state.job.address else state.job?.city}",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }

                            Text(
                                text = state.job?.industry.orEmpty(),
                                color = Color.White,
                                modifier = modifier.padding(top = 20.dp),
                                fontSize = 12.sp
                            )

                            Text(
                                text = state.job?.companyMotto.orEmpty(),
                                color = Color.White,
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
                                color = Color.White,
                                fontSize = 24.sp
                            )
                            HorizontalDivider(
                                modifier = modifier
                                    .padding(top = 12.dp)
                                    .width(120.dp),
                                thickness = 3.dp,
                                color = Color(0xFFD0BCFF)
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
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Column(
                                modifier = modifier.padding(top = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Job roles:",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Mobile Developer",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Column(
                                modifier = modifier.padding(top = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Tech stack/tooling used:",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                FlowRow {
                                    state.job?.skills.orEmpty().forEach {
                                        Text(
                                            text = it,
                                            color = Color.White,
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
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                FlowRow {
                                    state.job?.skills.orEmpty().forEach {
                                        Text(
                                            text = it,
                                            color = Color.White,
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
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Column(
                                modifier = modifier.padding(top = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Base Salary:",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = state.job?.salary.orEmpty(),
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Column(
                                modifier = modifier.padding(top = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Employment type:",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = state.job?.employmentType.orEmpty(),
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Column(
                                modifier = modifier.padding(top = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Remote working:",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = state.job?.workEnvironment.orEmpty(),
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Column(
                                modifier = modifier.padding(top = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Visa sponsorship:",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = state.job?.visaSponsorship.orEmpty(),
                                    color = Color.White,
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
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Column(
                                modifier = modifier.padding(top = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = state.job?.description.orEmpty(),
                                    color = Color.White,
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
                                color = Color.White,
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
                                                    text = bulletPoint,
                                                    color = Color.White
                                                )
                                            }
                                            Text(
                                                text = requirement,
                                                color = Color.White,
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
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Column(
                                modifier = modifier.padding(top = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = state.job?.aboutUs.orEmpty(),
                                    color = Color.White,
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
                                color = Color.White,
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
                                                    text = bulletPoint,
                                                    color = Color.White
                                                )
                                            }
                                            Text(
                                                text = benefit,
                                                color = Color.White,
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
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF8f8fa6),
                                    contentColor = Color(0xFF1C1C23)
                                )
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
