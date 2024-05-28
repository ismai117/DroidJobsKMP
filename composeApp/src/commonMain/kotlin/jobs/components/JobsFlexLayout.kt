package jobs.components


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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import jobs.domain.model.JobRole
import theme.LocalThemeIsDark


@Composable
fun JobsFlexLayout(
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState,
    jobs: List<JobRole>,
    openUrl: (String) -> Unit,
    shareLink: (String) -> Unit,
    navigateToDetailScreen: (JobRole) -> Unit
) {

    val isDark by LocalThemeIsDark.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            modifier = modifier
                .padding(top = 24.dp)
                .weight(1f),
            state = lazyGridState,
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            items(
                items = jobs.toSet().toList(),
                key = { it.id.hashCode() }
            ) { item ->

                OutlinedCard(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            navigateToDetailScreen(item)
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
                                    AsyncImage(
                                        model = item.companyLogo,
                                        contentDescription = item.company,
                                        modifier = modifier

                                            .size(50.dp)
                                            .clip(CircleShape)
                                    )
                                    Column(
                                        modifier = modifier
                                            .padding(start = 12.dp)
//                                        .border(width = 1.dp, color = Color.White)
                                    ) {
                                        Text(
                                            text = item.company,
                                            fontSize = 12.sp,
                                            modifier = modifier,
//                                            .border(width = 1.dp, color = Color.White),
                                            lineHeight = 1.em
                                        )
                                        Text(
                                            text = item.title,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
//                                        modifier = modifier.border(width = 1.dp, color = Color.White)
                                        )

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
                                                    text = "${item.city}, ${item.country}",
                                                    fontSize = 12.sp
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
                                                    fontSize = 12.sp
                                                )
                                            }
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    6.dp
                                                )
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.BarChart,
                                                    contentDescription = "level",
                                                )
                                                Text(
                                                    text = item.experienceLevel,
                                                    fontSize = 12.sp
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
                                                    fontSize = 12.sp
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
                                                shareLink(item.link)
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Share,
                                                contentDescription = "share job"
                                            )
                                        }
                                        Button(
                                            onClick = {
                                                openUrl(item.link)
                                            },
                                            modifier = modifier
                                                .width(100.dp)
                                                .height(30.dp),
                                            shape = RectangleShape,
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text(
                                                text = "APPLY",
                                                color = if (isDark) Color.White else Color.Black,
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

}
