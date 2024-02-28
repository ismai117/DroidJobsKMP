package di

import forgetPassword.di.ForgetPasswordModule
import login.di.LoginModule
import register.di.RegisterModule


object AuthModule {
    val loginModule: LoginModule by lazy { LoginModule() }
    val registerModule: RegisterModule by lazy { RegisterModule() }
    val forgetPasswordModule: ForgetPasswordModule by lazy { ForgetPasswordModule() }
}