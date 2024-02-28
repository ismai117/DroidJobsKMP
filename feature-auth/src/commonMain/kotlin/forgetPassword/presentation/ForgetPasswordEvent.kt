package forgetPassword.presentation

sealed class ForgetPasswordEvent {
    class EMAIL(val email: String) : ForgetPasswordEvent()
    data object SUBMIT : ForgetPasswordEvent()
    data object CLEAR : ForgetPasswordEvent()
    data object RESET_MESSAGE : ForgetPasswordEvent()
}