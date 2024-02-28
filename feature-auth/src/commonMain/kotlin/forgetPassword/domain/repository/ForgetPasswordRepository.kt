package forgetPassword.domain.repository

import kotlinx.coroutines.flow.Flow
import utils.utils.UIState


interface ForgetPasswordRepository {
   suspend fun forgetPassword(
        email: String
    ): Flow<UIState<Unit>>
}