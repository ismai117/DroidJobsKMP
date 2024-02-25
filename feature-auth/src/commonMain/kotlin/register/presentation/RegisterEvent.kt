package auth.register.presentation

sealed class RegisterEvent {
    class EMAIL(val email: String) :  RegisterEvent()
    class PASSWORD(val password: String) :  RegisterEvent()
    class CONFIRM_PASSWORD(val confirmPassword: String) :  RegisterEvent()
    object SUBMIT :  RegisterEvent()
    object CLEAR :  RegisterEvent()
}