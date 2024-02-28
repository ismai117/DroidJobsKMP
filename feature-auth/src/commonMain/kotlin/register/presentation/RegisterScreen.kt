package register.presentation

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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.ProgressBar
import components.SnackBarMessage
import di.AuthModule
import kotlinx.coroutines.launch
import login.presentation.LoginScreen
import platform.getPlatform


object RegisterScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class, InternalVoyagerApi::class)
    @Composable
    override fun Content() {


        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        val modifier: Modifier = Modifier

        val navigator = LocalNavigator.currentOrThrow
        val jobsScreen = rememberScreen(Screens.JobsScreen)


        val registerScreenModel = rememberScreenModel {
            RegisterScreenModel(
                registerRepository = AuthModule.registerModule.registerRepository
            )
        }

        val registerState = registerScreenModel.state

        LaunchedEffect(registerState.status){
            if (registerState.status){
                navigator.dispose(RegisterScreen)
                navigator.push(jobsScreen)
            }
        }

        LaunchedEffect(registerState.error){
            if (registerState.error.isNotBlank()){
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = registerState.error
                    )
                }
            }
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigator.pop()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                                contentDescription = "",
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

            Column (
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
//                    .border(width = 1.dp, color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Sign up",
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
                            value = registerState.email,
                            onValueChange = {
                                registerScreenModel.onEvent(
                                    RegisterEvent.EMAIL(it)
                                )
                            },
                            modifier = modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Email"
                                )
                            },
                            isError = registerState.emailError?.isNotBlank() == true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            )
                        )

                        if (registerState.emailError?.isNotBlank() == true) {
                            Text(
                                text = registerState.emailError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = modifier.padding(start = 12.dp)
                            )
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            value = registerState.password,
                            onValueChange = {
                                registerScreenModel.onEvent(
                                    RegisterEvent.PASSWORD(it)
                                )
                            },
                            modifier = modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Password"
                                )
                            },
                            isError = registerState.passwordError?.isNotBlank() == true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            )
                        )

                        if (registerState.passwordError?.isNotBlank() == true) {
                            Text(
                                text = registerState.passwordError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = modifier.padding(start = 12.dp)
                            )
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            value = registerState.confirmPassword,
                            onValueChange = {
                                registerScreenModel.onEvent(
                                    RegisterEvent.CONFIRM_PASSWORD(it)
                                )
                            },
                            modifier = modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Confirm Password"
                                )
                            },
                            isError = registerState.confirmPasswordError?.isNotBlank() == true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            )
                        )

                        if (registerState.confirmPasswordError?.isNotBlank() == true) {
                            Text(
                                text = registerState.confirmPasswordError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = modifier.padding(start = 12.dp)
                            )
                        }
                    }

                    Spacer(modifier = modifier.padding(12.dp))

                    Button(
                        onClick = {
                            registerScreenModel.onEvent(
                                RegisterEvent.SUBMIT
                            )
                        },
                        modifier = modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(
                            text = "Register"
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            navigator.push(LoginScreen)
                        },
                        modifier = modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                    ) {
                        Text(
                            text = "Login"
                        )
                    }


                }

            }

            ProgressBar(isLoading = registerState.isLoading)

        }

        DisposableEffect(Unit){
            onDispose {
                registerScreenModel.onEvent(
                    RegisterEvent.CLEAR
                )
            }
        }

    }

}
