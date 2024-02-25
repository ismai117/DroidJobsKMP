package register.presentation



data class RegisterState(

    val isLoading: Boolean = false,
    val status: Boolean= false,
    val error: String = "",

    val email: String = "",
    val emailError: String? = "",

    val password: String = "",
    val passwordError: String? = "",

    val confirmPassword: String = "",
    val confirmPasswordError: String? = ""

)