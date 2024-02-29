package register.di


import login.data.service.LoginService
import login.data.service.LoginServiceImpl
import register.data.repository.RegisterRepositoryImpl
import register.data.service.RegisterService
import register.data.service.RegisterServiceImpl
import register.domain.repository.RegisterRepository


class RegisterModule {

    private val registerService: RegisterService by lazy {
        RegisterServiceImpl()
    }

    private val loginService: LoginService by  lazy {
        LoginServiceImpl()
    }

    val registerRepository: RegisterRepository by lazy {
        RegisterRepositoryImpl(registerService, loginService)
    }

}