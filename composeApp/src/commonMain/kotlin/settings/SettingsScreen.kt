package settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp



@Composable
fun SettingsScreen(
    navigateBack:() -> Unit
) {

    SettingsScreenContent(
        navigateBack = navigateBack
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {

    var themeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings"
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
//        modifier = modifier.padding(
//            top = if (getPlatform().type == Platforms.DESKTOP) 24.dp else 0.dp
//        )
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            Column(
                modifier = modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = "General",
                    fontSize = 18.sp,
                    modifier = modifier.padding(start = 24.dp)
                )

                Column(
                    modifier = modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .height(55.dp)
                        .clickable {
                            themeDialog = true
                        },
//                           .border(width = 1.dp, color = Color.White),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Theme",
                        fontSize = 14.sp,
                        lineHeight = 1.em,
                        modifier = modifier.padding(start = 24.dp)
                    )
                    Text(
                        text = "Follow system",
                        fontSize = 14.sp,
                        lineHeight = 1.em,
                        modifier = modifier.padding(start = 24.dp)
                    )
                }

            }


        }

    }

//    ThemeDialog(
//        themeDialog = themeDialog,
//        themeDialogOnChange = {
//            themeDialog = it
//        }
//    )

}

