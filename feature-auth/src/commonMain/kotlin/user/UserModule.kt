package user

object UserModule {
    val userState : UserState by lazy {
        UserState()
    }
    val userDetail: UserDetail by lazy {
        UserDetail()
    }
}