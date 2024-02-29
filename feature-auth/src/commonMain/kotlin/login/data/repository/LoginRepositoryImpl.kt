package login.data.repository


import kotlinx.coroutines.channels.awaitClose
import login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import login.data.service.LoginService
import user.UserModule
import utils.utils.UIState


class LoginRepositoryImpl(
    private val loginService: LoginService
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<UIState<Unit>> = callbackFlow {
        runCatching {
            trySend(UIState.Loading())
            loginService.login(email, password)
        }.onSuccess { response ->
            when(response.status){
                200 -> {
                    UserModule.userState.setLoggedIn(true)
                    UserModule.userDetail.setUserDetail(
                        accessToken = response.body.access_token,
                        refreshToken = response.body.refresh_token,
                        userId = response.body.user_id,
                        deviceId = response.body.device_id
                    )
                    trySend(UIState.Success(null))
                }
                401 -> {
                    trySend(UIState.Error("Invalid email/password"))
                }
                else -> {
                    trySend(UIState.Error("Unexpected response code: ${response.status}"))
                }
            }
        }.onFailure { e ->
            trySend(UIState.Error("Unexpected Error!"))
            e.printStackTrace()
        }
        awaitClose {
            close()
        }
    }

    override fun logout() {
        UserModule.userState.setLoggedIn(false)
    }

}

