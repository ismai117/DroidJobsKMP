package login.data.fake


import UIState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class FakeLoginRepositoryImpl(
    private val fakeLoginServiceImpl: FakeLoginServiceImpl
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<UIState<Unit>> = callbackFlow {
        runCatching {
            trySend(UIState.Loading())
            delay(200)
            fakeLoginServiceImpl.login(email, password)
        }.onSuccess { response ->
            when(response.status){
                200 -> {
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

    }

}

