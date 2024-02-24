package di

import login.di.LoginModule
import register.di.RegisterModule


object AuthModule {
    val loginModule: LoginModule by lazy { LoginModule() }
    val registerModule: RegisterModule by lazy { RegisterModule() }
}