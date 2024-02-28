package login.data.repository


import io.ktor.client.call.body
import kotlinx.coroutines.channels.awaitClose
import login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import login.data.service.LoginResponse
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
        }.onSuccess {
            when(it.status.value){
                200 -> {
                    UserModule.userState.setLoggedIn(true)
                    val body = it.body<LoginResponse>()
                    UserModule.userDetail.setUserDetail(
                        accessToken = body.access_token,
                        refreshToken = body.refresh_token,
                        userId = body.user_id,
                        deviceId = body.device_id
                    )
                    trySend(UIState.Success(null))
                }
                401 -> {
                    trySend(UIState.Error("Invalid email/password"))
                }
                else -> {
                    trySend(UIState.Error("Unexpected response code: ${it.status.value}"))
                }
            }
        }.onFailure { e ->
            trySend(UIState.Error(e.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun logout() {
        UserModule.userState.setLoggedIn(false)
    }

}

