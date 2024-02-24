package login.presentation


sealed class LoginEvent {
    class EMAIL(val email: String) :  LoginEvent()
    class PASSWORD(val password: String) : LoginEvent()
    object SUBMIT : LoginEvent()
    object CLEAR : LoginEvent()
    class SHOW_PASSWORD(val visible: Boolean) : LoginEvent()
    object RESET_MESSAGE : LoginEvent()
}