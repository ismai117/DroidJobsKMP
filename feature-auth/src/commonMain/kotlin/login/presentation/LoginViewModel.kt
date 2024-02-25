package login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import login.domain.repository.LoginRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utils.utils.UIState
import utils.validations.ValidateEmail
import utils.validations.ValidatePassword

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ScreenModel {

    var state by mutableStateOf(LoginState())

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EMAIL -> {
                state = state.copy(email = event.email)
            }

            is LoginEvent.PASSWORD -> {
                state = state.copy(password = event.password)
            }

            is LoginEvent.SUBMIT -> {
                submit()
            }

            is LoginEvent.CLEAR -> {
                clearValues()
            }

            is LoginEvent.SHOW_PASSWORD -> {
                state = state.copy(passwordVisible = event.visible)
            }

            is LoginEvent.RESET_MESSAGE -> {
                state = state.copy(error = "")
            }
        }
    }

    private fun submit() {

        val emailResult = ValidateEmail(state.email)
        val passwordResult = ValidatePassword(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        screenModelScope.launch{
            loginRepository.login(
                email = state.email,
                password = state.password
            ).collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is UIState.Loading -> {
                            state = state.copy(
                                isLoading = true,
                                status = false
                            )
                        }

                        is  UIState.Success -> {
                            state = state.copy(
                                isLoading = false,
                                status = true,
                            )
                        }

                        is  UIState.Error -> {
                            state = state.copy(
                                isLoading = false,
                                status = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun logout(){
        screenModelScope.launch {
            loginRepository.logout()
        }
    }

    private fun clearValues() {
        state = state.copy(
            isLoading = false,
            status = false,
            error = "",
            email = "",
            emailError = "",
            password = "",
            passwordError = "",
            passwordVisible = false
        )
    }


}