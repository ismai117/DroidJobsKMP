package di

import forgetPassword.di.forgetPasswordModule
import login.di.loginModule
import org.koin.dsl.module
import register.di.registerModule


val authModule = module {
    includes(
        loginModule,
        registerModule,
        forgetPasswordModule
    )
}