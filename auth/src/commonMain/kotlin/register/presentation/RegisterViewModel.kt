package register.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import register.domain.repository.RegisterRepository
import auth.register.presentation.RegisterEvent
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import utils.utils.UIState
import utils.validations.ValidateConfirmPassword
import utils.validations.ValidateEmail
import utils.validations.ValidatePassword

class RegisterViewModel (
    private val registerRepository: RegisterRepository
) : ScreenModel {


    var state by mutableStateOf(RegisterState())

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EMAIL -> {
                state = state.copy(email = event.email)
            }

            is RegisterEvent.PASSWORD -> {
                state = state.copy(password = event.password)
            }

            is RegisterEvent.CONFIRM_PASSWORD -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }

            is RegisterEvent.SUBMIT -> {
                submit()
            }

            is RegisterEvent.CLEAR -> {
                clearValues()
            }
        }
    }

    private fun submit() {

        val emailResult = ValidateEmail(state.email)
        val passwordResult = ValidatePassword(state.password)
        val confirmPasswordResult = ValidateConfirmPassword(state.password, state.confirmPassword)

        val hasError = listOf(
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = confirmPasswordResult.errorMessage
            )
            return
        }

        screenModelScope.launch{
            registerRepository.register(
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

                    is UIState.Success -> {
                        state = state.copy(
                            isLoading = false,
                            status = true
                        )
                    }

                    is UIState.Error -> {
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

    private fun clearValues() {
        state = state.copy(
            isLoading = false,
            status = false,
            error = "",
            email = "",
            emailError = "",
            password = "",
            passwordError = "",
            confirmPassword = "",
            confirmPasswordError = ""
        )
    }


}