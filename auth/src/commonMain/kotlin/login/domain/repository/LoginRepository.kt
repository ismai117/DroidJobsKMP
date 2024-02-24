package login.domain.repository

import kotlinx.coroutines.flow.Flow
import utils.utils.UIState


interface LoginRepository {
   suspend fun login(
        email: String,
        password: String
    ): Flow<UIState<Unit>>
   fun logout()
//    fun setUserDetail(userDetail: UserDetail)
//    fun getUserDetail(): UserDetail?
}