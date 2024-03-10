package forgetPassword.data.repository

import UIState
import forgetPassword.domain.repository.ForgetPasswordRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class ForgetPasswordRepositoryImpl : ForgetPasswordRepository{

    override suspend fun forgetPassword(
        email: String
    ): Flow<UIState<Unit>>  = callbackFlow {
        try {
            trySend(UIState.Loading())
            delay(2000)
            trySend(UIState.Success(null))
        } catch (e: Exception) {
            trySend(UIState.Error(e.message.toString()))
        }
        awaitClose {
            cancel()
        }
    }

}