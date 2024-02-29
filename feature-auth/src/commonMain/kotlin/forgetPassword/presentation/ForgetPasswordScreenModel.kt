package forgetPassword.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import forgetPassword.domain.repository.ForgetPasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import login.presentation.LoginEvent
import utils.utils.UIState
import validations.email.ValidateEmail


class ForgetPasswordScreenModel(
    private val forgetPasswordRepository: ForgetPasswordRepository
) : ScreenModel {

    var state by mutableStateOf(ForgetPasswordState())

    fun onEvent(event: ForgetPasswordEvent){
        when(event){
            is ForgetPasswordEvent.EMAIL -> {
                state = state.copy(email = event.email)
            }
            is ForgetPasswordEvent.SUBMIT -> {
                submit()
            }
            is ForgetPasswordEvent.CLEAR -> {
                clearValues()
            }
            is ForgetPasswordEvent.RESET_MESSAGE -> {
                state = state.copy(error = "")
            }
        }
    }

    private fun submit() {

        val emailResult = ValidateEmail(state.email)

        val hasError = !emailResult.successful

        if (hasError){
            state = state.copy(
                emailError = emailResult.errorMessage
            )
            return
        }

        screenModelScope.launch{
            forgetPasswordRepository.forgetPassword(
                email = state.email,
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

    private fun clearValues() {
        state = state.copy(
            isLoading = false,
            status = false,
            error = "",
            email = "",
            emailError = ""
        )
    }

}