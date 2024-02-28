package register.data.repository

import io.ktor.client.call.body
import register.domain.repository.RegisterRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import login.data.service.LoginResponse
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
        }.onSuccess {
            when(it.status.value){
                201 -> {
                    val loginResponse = loginService.login(email, password)
                    if (loginResponse.status.value == 200) {
                        UserModule.userState.setLoggedIn(true)
                        val body = loginResponse.body<LoginResponse>()
                        UserModule.userDetail.setUserDetail(
                            accessToken = body.access_token,
                            refreshToken = body.refresh_token,
                            userId = body.user_id,
                            deviceId = body.device_id
                        )
                        trySend(UIState.Success(null))
                    }
                }
                409 -> {
                    trySend(UIState.Error("User already exists"))
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

}

