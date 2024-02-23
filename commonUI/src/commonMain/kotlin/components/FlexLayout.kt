package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Jobs
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun FlexLayout(
    modifier: Modifier = Modifier,
    jobs: List<Jobs>,
    navigateToDetailScreen: (String) -> Unit,
    openUrl: (String) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier
//                    .border(width = 1.dp, color = Color.White)
            .padding(top = 24.dp),
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        items(
            items = jobs,
            key = { it.id }
        ) { item ->

            ElevatedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(
                        width = 2.dp,
                        color = Color(0xFFCFCFFC).copy(0.15f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        navigateToDetailScreen(item.id)
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1C1C23)
                )
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
                            Row {
                                Image(
                                    painter = painterResource(DrawableResource("drawable/" + item.companyLogo)),
                                    contentDescription = "${item.company} icon",
                                    modifier = modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                )
                                Column(
                                    modifier = modifier
                                        .padding(start = 12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = item.company,
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        text = item.title,
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
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
                                                contentDescription = "location",
                                                tint = Color.White
                                            )
                                            Text(
                                                text = "${item.city}, ${item.country}",
                                                color = Color.White,
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
                                                tint = Color.White
                                            )
                                            Text(
                                                text = item.workEnvironment,
                                                color = Color.White,
                                                fontSize = 12.sp
                                            )
                                        }
                                        Text(
                                            text = item.experienceLevel,
                                            color = Color.White,
                                            fontSize = 12.sp
                                        )
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
                                                color = Color.White,
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
                                    color = Color.White,
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

                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Share,
                                            contentDescription = "share job",
                                            tint = Color.White
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            openUrl(item.link)
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
                    }
                }
            }
        }

    }

}