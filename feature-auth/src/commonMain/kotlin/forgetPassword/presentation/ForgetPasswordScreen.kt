package forgetPassword.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.ProgressBar
import components.SnackBarMessage
import di.AuthModule
import kotlinx.coroutines.launch
import login.presentation.LoginScreen
import platform.getPlatform


@OptIn(InternalVoyagerApi::class)
object ForgetPasswordScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val forgetPasswordScreenModel = rememberScreenModel {
            ForgetPasswordScreenModel(
                forgetPasswordRepository = AuthModule.forgetPasswordModule.forgetPasswordRepository
            )
        }

        val forgetPasswordState = forgetPasswordScreenModel.state

        ForgetPasswordContent(
            forgetPasswordState = forgetPasswordState,
            onEvent = {
                forgetPasswordScreenModel.onEvent(it)
            },
            navigateToLoginScreen = {
                navigator.dispose(ForgetPasswordScreen)
                navigator.push(LoginScreen)
            },
            navigateBack = {
                navigator.pop()
            }
        )

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordContent(
    modifier: Modifier = Modifier,
    forgetPasswordState: ForgetPasswordState,
    onEvent: (ForgetPasswordEvent) -> Unit,
    navigateToLoginScreen: () -> Unit,
    navigateBack: () -> Unit
){

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(forgetPasswordState.status) {
        if (forgetPasswordState.status) {
           navigateToLoginScreen()
        }
    }

    LaunchedEffect(forgetPasswordState.error) {
        if (forgetPasswordState.error.isNotBlank()) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = forgetPasswordState.error
                )
            }
            onEvent(
                ForgetPasswordEvent.RESET_MESSAGE
            )
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
                modifier = modifier.fillMaxWidth()
            )
        },
        snackbarHost = {
            SnackBarMessage(
                snackBarHostState = snackbarHostState,
                onDismiss = {}
            )
        },
        modifier = modifier.padding(
            top = if (getPlatform().name == "Desktop") 24.dp else 0.dp
        )
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
//                    .border(width = 1.dp, color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Reset Password",
                fontSize = 32.sp,
                modifier = modifier
                    .padding(top = 80.dp)
//                        .border(width = 1.dp, color = Color.White)
            )

            Column(
                modifier = modifier
                    .padding(top = 60.dp, start = 24.dp, end = 24.dp)
                    .widthIn(max = 330.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    OutlinedTextField(
                        value = forgetPasswordState.email,
                        onValueChange = {
                            onEvent(
                                ForgetPasswordEvent.EMAIL(it)
                            )
                        },
                        modifier = modifier.fillMaxWidth(),
                        label = {
                            Text(
                                text = "Email"
                            )
                        },
                        isError = forgetPasswordState.emailError?.isNotBlank() == true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )

                    if (forgetPasswordState.emailError?.isNotBlank() == true) {
                        Text(
                            text = forgetPasswordState.emailError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = modifier.padding(start = 12.dp)
                        )
                    }
                }

                Spacer(modifier = modifier.padding(12.dp))

                Button(
                    onClick = {
                        onEvent(
                            ForgetPasswordEvent.SUBMIT
                        )
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Submit"
                    )
                }
            }

        }

        ProgressBar(isLoading = forgetPasswordState.isLoading)

    }

    DisposableEffect(Unit) {
        onDispose {
            onEvent(
                ForgetPasswordEvent.CLEAR
            )
        }
    }

}