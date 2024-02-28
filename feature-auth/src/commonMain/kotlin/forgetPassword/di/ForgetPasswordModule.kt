package forgetPassword.di

import forgetPassword.data.repository.ForgetPasswordRepositoryImpl
import forgetPassword.domain.repository.ForgetPasswordRepository


class ForgetPasswordModule {

    val forgetPasswordRepository: ForgetPasswordRepository by lazy {
        ForgetPasswordRepositoryImpl()
    }

}