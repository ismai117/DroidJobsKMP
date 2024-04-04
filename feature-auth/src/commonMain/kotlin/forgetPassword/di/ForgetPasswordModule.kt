package forgetPassword.di

import forgetPassword.data.repository.ForgetPasswordRepositoryImpl
import forgetPassword.domain.repository.ForgetPasswordRepository
import forgetPassword.presentation.ForgetPasswordViewModel
import org.koin.dsl.module


val forgetPasswordModule = module {
    single<ForgetPasswordRepository> { ForgetPasswordRepositoryImpl() }
    factory { ForgetPasswordViewModel(get()) }
}
