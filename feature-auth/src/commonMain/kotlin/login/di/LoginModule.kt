package login.di

import login.data.repository.LoginRepositoryImpl
import login.data.service.LoginService
import login.data.service.LoginServiceImpl
import login.domain.repository.LoginRepository

class LoginModule {

    private val loginService: LoginService by lazy {
        LoginServiceImpl()
    }

    val loginRepository: LoginRepository by lazy {
        LoginRepositoryImpl(loginService)
    }

}