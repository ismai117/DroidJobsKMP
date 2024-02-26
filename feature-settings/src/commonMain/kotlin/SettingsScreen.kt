import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import platform.getPlatform
import user.UserModule


object SettingsScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class, InternalVoyagerApi::class)
    @Composable
    override fun Content() {

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow
        val loginScreen = rememberScreen(Screens.LoginScreen)

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
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {


                Button(
                    onClick = {
                        UserModule.userState.setLoggedIn(false)
                        navigator.popUntilRoot()
                        navigator.push(loginScreen)
                    }
                ){
                    Text(
                        text = "Logout"
                    )
                }


            }

        }

    }

}