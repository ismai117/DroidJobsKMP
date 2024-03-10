package forgetPassword.domain.repository

import UIState
import kotlinx.coroutines.flow.Flow


interface ForgetPasswordRepository {
   suspend fun forgetPassword(
        email: String
    ): Flow<UIState<Unit>>
}