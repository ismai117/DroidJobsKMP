package register.di


import login.data.service.LoginService
import register.data.repository.RegisterRepositoryImpl
import register.data.service.RegisterService
import register.domain.repository.RegisterRepository


class RegisterModule {

    private val registerService: RegisterService by lazy {
        RegisterService()
    }

    private val loginService: LoginService by  lazy {
        LoginService()
    }

    val registerRepository: RegisterRepository by lazy {
        RegisterRepositoryImpl(registerService, loginService)
    }

}