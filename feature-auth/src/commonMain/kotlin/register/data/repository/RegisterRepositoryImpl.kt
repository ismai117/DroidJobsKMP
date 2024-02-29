package register.data.repository


import register.domain.repository.RegisterRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import login.data.service.LoginService
import register.data.service.RegisterService
import user.UserModule
import utils.utils.UIState


class RegisterRepositoryImpl(
    private val registerService: RegisterService,
    private val loginService: LoginService
) : RegisterRepository {

    override suspend fun register(
        email: String,
        password: String
    ): Flow<UIState<Unit>> = callbackFlow {
        runCatching {
            trySend(UIState.Loading())
            registerService.register(email, password)
        }.onSuccess { registerResponse ->
            when(registerResponse.status){
                201 -> {
                    val loginResponse = loginService.login(email, password)
                    if (registerResponse.status == 200) {
                        UserModule.userState.setLoggedIn(true)
                        UserModule.userDetail.setUserDetail(
                            accessToken = loginResponse.body.access_token,
                            refreshToken = loginResponse.body.refresh_token,
                            userId = loginResponse.body.user_id,
                            deviceId = loginResponse.body.device_id
                        )
                        trySend(UIState.Success(null))
                    }
                }
                409 -> {
                    trySend(UIState.Error("User already exists"))
                }
                else -> {
                    trySend(UIState.Error("Unexpected response code: ${registerResponse.status}"))
                }
            }
        }.onFailure { e ->
            trySend(UIState.Error(e.message.toString()))
        }
        awaitClose {
            close()
        }
    }

}

