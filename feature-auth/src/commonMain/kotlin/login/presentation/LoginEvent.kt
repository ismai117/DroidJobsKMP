package login.presentation


sealed class LoginEvent {
    class EMAIL(val email: String) :  LoginEvent()
    class PASSWORD(val password: String) : LoginEvent()
    data object SUBMIT : LoginEvent()
    data object CLEAR : LoginEvent()
    class SHOW_PASSWORD(val visible: Boolean) : LoginEvent()
    data object RESET_MESSAGE : LoginEvent()
}