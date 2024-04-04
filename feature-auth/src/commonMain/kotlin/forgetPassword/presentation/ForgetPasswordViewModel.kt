package forgetPassword.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import forgetPassword.domain.repository.ForgetPasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import validations.email.ValidateEmail


class ForgetPasswordViewModel(
    private val forgetPasswordRepository: ForgetPasswordRepository
) : ViewModel() {

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

        viewModelScope.launch(Dispatchers.Default) {
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