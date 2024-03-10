package register.data.fake


import UIState
import kotlinx.coroutines.cancel
import register.domain.repository.RegisterRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class FakeRegisterRepositoryImpl : RegisterRepository {

    override suspend fun register(
        email: String,
        password: String
    ): Flow<UIState<Unit>> = callbackFlow {
        try {
            trySend(UIState.Loading())
            delay(200)
            trySend(UIState.Success(null))
        } catch (e: Exception) {
            trySend(UIState.Error(e.message.toString()))
        }
        awaitClose {
            cancel()
        }
    }

}

