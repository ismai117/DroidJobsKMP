package register.di


import register.data.repository.RegisterRepositoryImpl
import register.domain.repository.RegisterRepository


class RegisterModule {

    val registerRepository: RegisterRepository by lazy {
        RegisterRepositoryImpl()
    }

}