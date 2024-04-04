package login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import login.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import validations.email.ValidateEmail
import validations.email.ValidateLoginPassword


data class AppDispatchers(
    val IO: CoroutineDispatcher = Dispatchers.Default
)

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

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
        val passwordResult = ValidateLoginPassword(state.password)

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

        viewModelScope.launch(Dispatchers.Default) {
            loginRepository.login(
                email = state.email,
                password = state.password
            ).collect { result ->
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

    fun logout(){
        viewModelScope.launch(Dispatchers.Default) {
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