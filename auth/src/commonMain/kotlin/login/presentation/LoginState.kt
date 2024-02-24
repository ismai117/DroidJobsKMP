package login.presentation


data class LoginState(

    val isLoading: Boolean = false,
    val status: Boolean= false,
    val error: String = "",

    val email: String = "",
    val emailError: String? = "",

    val password: String = "",
    val passwordError: String? = "",

    val passwordVisible: Boolean = false


)