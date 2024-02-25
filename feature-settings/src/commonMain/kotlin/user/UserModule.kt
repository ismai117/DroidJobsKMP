package user

object UserModule {
    val userState : UserState by lazy {
        UserState()
    }
}