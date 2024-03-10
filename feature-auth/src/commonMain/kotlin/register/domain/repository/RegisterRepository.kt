package register.domain.repository

import UIState
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
  suspend fun register(
        email: String,
        password: String
    ): Flow<UIState<Unit>>
}