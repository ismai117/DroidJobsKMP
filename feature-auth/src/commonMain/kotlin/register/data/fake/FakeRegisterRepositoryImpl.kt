package register.data.fake


import kotlinx.coroutines.cancel
import register.domain.repository.RegisterRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import utils.utils.UIState


class FakeRegisterRepositoryImpl : RegisterRepository {

    override suspend fun register(
        email: String,
        password: String
    ): Flow<UIState<Unit>> = callbackFlow {
        try {
            trySend(UIState.Loading())
//            delay(2000)
            trySend(UIState.Success(null))
        } catch (e: Exception) {
            trySend(UIState.Error(e.message.toString()))
        }
        awaitClose {
            cancel()
        }
    }

}
