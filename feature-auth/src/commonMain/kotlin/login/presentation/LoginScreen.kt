package login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.LoadingButton
import components.ProgressBar
import components.SnackBarMessage
import di.AuthModule
import forgetPassword.presentation.ForgetPasswordScreen
import kotlinx.coroutines.launch
import platform.getPlatform
import register.presentation.RegisterScreen

@OptIn(InternalVoyagerApi::class)
object LoginScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val jobsScreen = rememberScreen(Screens.JobsScreen)

        val loginScreenModel = rememberScreenModel {
            LoginScreenModel(
                loginRepository = AuthModule.loginModule.loginRepository
            )
        }

        val loginState = loginScreenModel.state

        LoginScreenContent(
            loginState = loginState,
            onEvent = {
                loginScreenModel.onEvent(it)
            },
            navigateToJobsScreen = {
                navigator.dispose(LoginScreen)
                navigator.push(jobsScreen)
            },
            navigateToRegisterScreen = {
                navigator.push(RegisterScreen)
            },
            navigateToForgetPasswordScreen = {
                navigator.push(ForgetPasswordScreen)
            }
        )

    }

}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    loginState: LoginState,
    onEvent: (LoginEvent) -> Unit,
    navigateToJobsScreen: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
    navigateToForgetPasswordScreen: () -> Unit,
){

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(loginState.status){
        if(loginState.status){
           navigateToJobsScreen()
        }
    }

    LaunchedEffect(loginState.error){
        if (loginState.error.isNotBlank()){
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = loginState.error
                )
            }
            onEvent(
                LoginEvent.RESET_MESSAGE
            )
        }
    }

    Scaffold(
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

        Column (
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
//                    .border(width = 1.dp, color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Sign in",
                fontSize = 32.sp,
                modifier = modifier
                    .padding(top = 100.dp)
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
                        value = loginState.email,
                        onValueChange = {
                            onEvent(
                                LoginEvent.EMAIL(it)
                            )
                        },
                        modifier = modifier.fillMaxWidth(),
                        label = {
                            Text(
                                text = "E-mail"
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "email"
                            )
                        },
                        isError = loginState.emailError?.isNotBlank() == true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )

                    if (!loginState.emailError.isNullOrBlank()) {
                        Text(
                            text = loginState.emailError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = modifier.padding(start = 12.dp)
                        )
                    }

                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    OutlinedTextField(
                        value = loginState.password,
                        onValueChange = {
                            onEvent(
                                LoginEvent.PASSWORD(it)
                            )
                        },
                        modifier = modifier.fillMaxWidth(),
                        label = {
                            Text(
                                text = "Password"
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = "Password"
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    if (!loginState.passwordVisible){
                                        onEvent(
                                            LoginEvent.SHOW_PASSWORD(visible = true)
                                        )
                                    }else{
                                        onEvent(
                                            LoginEvent.SHOW_PASSWORD(visible = false)
                                        )
                                    }
                                }
                            ){
                                Icon(
                                    imageVector = if (loginState.passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                    contentDescription = "Show password",
                                )
                            }
                        },
                        isError = loginState.passwordError?.isNotBlank() == true,
                        visualTransformation = if (loginState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        )
                    )

                    if (!loginState.passwordError.isNullOrBlank()) {
                        Text(
                            text = loginState.passwordError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = modifier.padding(start = 12.dp)
                        )
                    }
                }

                TextButton(
                    onClick = {
                       navigateToForgetPasswordScreen()
                    },
                    modifier = modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Forget Password"
                    )
                }

                LoadingButton(
                    onClick = {
                        onEvent(
                            LoginEvent.SUBMIT
                        )
                    },
                    label = "Login",
                    isLoading = loginState.isLoading
                )

                OutlinedButton(
                    onClick = {
                       navigateToRegisterScreen()
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Text(
                        text = "Register"
                    )
                }

            }

        }

    }

    DisposableEffect(Unit){
        onDispose {
            onEvent(
                LoginEvent.CLEAR
            )
        }
    }


}


