package register.data.repository

import register.domain.repository.RegisterRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import user.UserModule
import utils.utils.UIState


class RegisterRepositoryImpl : RegisterRepository {

    override suspend fun register(
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

}

