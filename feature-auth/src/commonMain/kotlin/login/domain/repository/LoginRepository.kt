package login.domain.repository

import UIState
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
   suspend fun login(
        email: String,
        password: String
    ): Flow<UIState<Unit>>
   fun logout()
//    fun setUserDetail(userDetail: UserDetail)
//    fun getUserDetail(): UserDetail?
}