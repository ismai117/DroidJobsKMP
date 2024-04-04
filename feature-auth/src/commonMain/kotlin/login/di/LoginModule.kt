package login.di

import login.data.repository.LoginRepositoryImpl
import login.data.service.LoginService
import login.data.service.LoginServiceImpl
import login.domain.repository.LoginRepository
import login.presentation.LoginViewModel
import org.koin.dsl.module


val loginModule = module {
    single<LoginService> { LoginServiceImpl() }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    factory { LoginViewModel(get()) }
}
