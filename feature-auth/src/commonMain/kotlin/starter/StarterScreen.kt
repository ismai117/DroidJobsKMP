package starter


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.SignalCellularAlt
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.CompanyLogo
import login.presentation.LoginScreen
import platform.getPlatform
import register.presentation.RegisterScreen
import starter.jobs.Jobs
import starter.jobs.StarterJobsService
import theme.LocalThemeIsDark


object StarterScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow

        var boxWidth by remember { mutableStateOf(768.dp) }

        var isDark by LocalThemeIsDark.current

        val starterJobsService = StarterJobsService()
        val jobs = starterJobsService.getAllJobs()

        var openDrawer by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "DroidJobs",
                            lineHeight = 1.em
                        )
                    },
                    actions = {
                        if (boxWidth < 768.dp) {
                            IconButton(
                                onClick = {
                                    openDrawer = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = ""
                                )
                            }
                        } else {
                            TextButton(
                                onClick = {
                                    navigator.pop()
                                    navigator.push(LoginScreen)
                                }
                            ) {
                                Text(
                                    text = "Sign in",
                                    lineHeight = 1.em
                                )
                            }
                            TextButton(
                                onClick = {
                                    navigator.pop()
                                    navigator.push(RegisterScreen)
                                }
                            ) {
                                Text(
                                    text = "Sign up",
                                    lineHeight = 1.em
                                )
                            }
                            IconButton(
                                onClick = { isDark = !isDark }
                            ) {
                                Icon(
                                    imageVector = if (isDark) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },
            modifier = modifier.padding(
                top = if (getPlatform().name == "Desktop") 24.dp else 0.dp
            ),
            contentWindowInsets = WindowInsets(0.dp)
        ) { paddingValues ->
            BoxWithConstraints(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                boxWidth = maxWidth.value.dp

                FlexLayout(
                    jobs = jobs,
                    navigateToLoginScreen = {
                        navigator.push(LoginScreen)
                    }
                )

            }

            if (openDrawer && boxWidth < 768.dp){
                ModalBottomSheet(
                    onDismissRequest = {
                        openDrawer = false
                    },
                    modifier = modifier.fillMaxWidth()
                ){
                    Column(
                        modifier = modifier.fillMaxSize()
                    ) {
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .clickable {
                                    openDrawer = false
                                    navigator.push(LoginScreen)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ){
                                Text(
                                    text = "Sign in",
                                    modifier = modifier.padding(start = 16.dp),
                                    lineHeight = 1.em
                                )
                            }
                        }
                        HorizontalDivider()
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .clickable {
                                    openDrawer = false
                                    navigator.push(RegisterScreen)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ){
                                Text(
                                    text = "Sign up",
                                    modifier = modifier.padding(start = 16.dp),
                                    lineHeight = 1.em
                                )
                            }
                        }
                        HorizontalDivider()
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .clickable {
                                    isDark = !isDark
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ){
                                Text(
                                    text = "Theme",
                                    modifier = modifier.padding(start = 16.dp),
                                    lineHeight = 1.em
                                )
                            }
                        }
                    }
                }
            } else {
                openDrawer = false
            }

        }


    }

}


@Composable
fun FlexLayout(
    modifier: Modifier = Modifier,
    jobs: List<Jobs>,
    navigateToLoginScreen: () -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier
            .padding(top = 24.dp),
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        items(
            items = jobs.toSet().toList(),
            key = { it.id }
        ) { item ->

            OutlinedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                       navigateToLoginScreen()
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.outlinedCardColors()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
//                                    .border(width = 1.dp, color = Color.White)
                    ) {
                        Column {
                            Row(
                                modifier = modifier,
//                                    .border(width = 1.dp, color = Color.White),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CompanyLogo(
                                    modifier = modifier
                                        .size(50.dp)
                                        .clip(CircleShape),
                                    companyLogo = item.companyLogo,
                                    companyName = item.company
                                )
                                Column(
                                    modifier = modifier
                                        .padding(start = 12.dp)
//                                        .border(width = 1.dp, color = Color.White)
                                ) {

                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(3.dp)
                                    ) {
                                        Text(
                                            text = item.company,
                                            fontSize = 12.sp,
                                            lineHeight = 1.em,
                                            modifier = modifier,
//                                            .border(width = 1.dp, color = Color.White)
                                        )

                                        Text(
                                            text = item.title,
                                            fontSize = 14.sp,
                                            lineHeight = 1.em,
                                            fontWeight = FontWeight.Bold
//                                        modifier = modifier.border(width = 1.dp, color = Color.White)
                                        )
                                    }

                                    Column(
                                        modifier = modifier.padding(top = 12.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(
                                                6.dp
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.LocationOn,
                                                contentDescription = "location"
                                            )
                                            Text(
                                                text = when {
                                                    item.city.isBlank() -> item.country
                                                    item.country.isBlank() -> item.city
                                                    item.city.isNotBlank() && item.country.isNotBlank() -> "${item.city}, ${item.country}"
                                                    else -> ""
                                                },
                                                fontSize = 12.sp,
                                                lineHeight = 1.em
                                            )
                                        }
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(
                                                6.dp
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Workspaces,
                                                contentDescription = "work environment",
                                            )
                                            Text(
                                                text = item.workEnvironment,
                                                fontSize = 12.sp,
                                                lineHeight = 1.em
                                            )
                                        }
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(
                                                6.dp
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.SignalCellularAlt,
                                                contentDescription = "work environment"
                                            )

                                            Text(
                                                text = item.experienceLevel,
                                                fontSize = 12.sp,
                                                lineHeight = 1.em
                                            )
                                        }
                                    }

                                }
                            }
                            LazyRow(
                                modifier = modifier.padding(top = 12.dp)
                            ) {
                                items(
                                    items = item.skills
                                ) { skill ->
                                    SuggestionChip(
                                        onClick = {},
                                        label = {
                                            Text(
                                                text = skill,
                                                fontSize = 12.sp,
                                                lineHeight = 1.em
                                            )
                                        },
                                        modifier = modifier.padding(end = 12.dp),
                                        enabled = false,
                                        colors = SuggestionChipDefaults.suggestionChipColors(
                                            containerColor = Color.Transparent
                                        )
                                    )
                                }
                            }
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
//                                            .border(width = 1.dp, color = Color.White)
                            ) {

                                Text(
                                    text = "Posted: " + item.postedDate,
                                    fontSize = 12.sp,
                                    lineHeight = 1.em,
                                    modifier = modifier.align(Alignment.CenterStart)
                                )

                                Row(
                                    modifier = modifier
//                                                .border(width = 1.dp, color = Color.White)
                                        .align(Alignment.CenterEnd),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
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
                            }
                        }
                    }
                }
            }
        }

    }

}