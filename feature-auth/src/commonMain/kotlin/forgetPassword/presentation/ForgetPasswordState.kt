package forgetPassword.presentation



data class ForgetPasswordState(

    val isLoading: Boolean = false,
    val status: Boolean= false,
    val error: String = "",

    val email: String = "",
    val emailError: String? = ""

)