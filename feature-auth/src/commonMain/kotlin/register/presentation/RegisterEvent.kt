package register.presentation

sealed class RegisterEvent {
    class EMAIL(val email: String) :  RegisterEvent()
    class PASSWORD(val password: String) :  RegisterEvent()
    class CONFIRM_PASSWORD(val confirmPassword: String) :  RegisterEvent()
    data object SUBMIT :  RegisterEvent()
    data object CLEAR :  RegisterEvent()
}