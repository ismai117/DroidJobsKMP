package login.data.repository

import login.domain.repository.LoginRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import user.UserModule
import utils.utils.UIState


class LoginRepositoryImpl : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<UIState<Unit>> = callbackFlow {
        try {
            trySend(UIState.Loading())
            delay(2000)
            UserModule.userState.setLoggedIn(true)
            trySend(UIState.Success(null))
        } catch (e: Exception) {
            trySend(UIState.Error(e.message.toString()))
        }
        awaitClose {
            cancel()
        }
    }

    override fun logout() {

    }

}
