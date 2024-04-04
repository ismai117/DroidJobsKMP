package register.di


import login.data.service.LoginService
import login.data.service.LoginServiceImpl
import org.koin.dsl.module
import register.data.repository.RegisterRepositoryImpl
import register.data.service.RegisterService
import register.data.service.RegisterServiceImpl
import register.domain.repository.RegisterRepository
import register.presentation.RegisterViewModel


val registerModule = module {
    single<RegisterService> { RegisterServiceImpl() }
    single<RegisterRepository> {
        RegisterRepositoryImpl(get(), get())
    }
    factory { RegisterViewModel(get()) }
}
