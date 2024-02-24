package register.domain.repository

import kotlinx.coroutines.flow.Flow
import utils.utils.UIState

interface RegisterRepository {
  suspend fun register(
        email: String,
        password: String
    ): Flow<UIState<Unit>>
}