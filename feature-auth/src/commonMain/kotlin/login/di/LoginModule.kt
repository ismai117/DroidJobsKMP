package login.di

import login.data.repository.LoginRepositoryImpl
import login.domain.repository.LoginRepository

class LoginModule {

    val loginRepository: LoginRepository by lazy {
        LoginRepositoryImpl()
    }

}