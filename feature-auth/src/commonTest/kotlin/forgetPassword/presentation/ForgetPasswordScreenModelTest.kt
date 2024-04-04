package forgetPassword.presentation

import forgetPassword.data.fake.FakeForgetPasswordRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ForgetPasswordScreenModelTest {

    private lateinit var screenModel: ForgetPasswordViewModel

    @BeforeTest
    fun setUp(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        screenModel = ForgetPasswordViewModel(
            forgetPasswordRepository = FakeForgetPasswordRepositoryImpl()
        )
    }

    @AfterTest
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun blankEmailErrorShown(){
        screenModel.onEvent(ForgetPasswordEvent.EMAIL(""))
        screenModel.onEvent(ForgetPasswordEvent.SUBMIT)
        assertEquals(
            screenModel.state.emailError,
            "Email can't be blank!",
            "'Email can't be blank!' error message should be shown"
        )
    }

    @Test
    fun invalidEmailErrorShown(){
        screenModel.onEvent(ForgetPasswordEvent.EMAIL("ishyy17gmail.com"))
        screenModel.onEvent(ForgetPasswordEvent.SUBMIT)
        assertEquals(
            screenModel.state.emailError,
            "Email is not valid!",
            "'Email is not valid!' error message should be shown"
        )
    }

    @Test
    fun checkForgetPasswordIsLoading() = runTest{
        screenModel.onEvent(ForgetPasswordEvent.EMAIL("ishyfaisal@gmail.com"))
        assertEquals(true, screenModel.state.emailError?.isBlank(), screenModel.state.emailError)
        screenModel.onEvent(ForgetPasswordEvent.SUBMIT)
        assertEquals(true, screenModel.state.isLoading, "isLoading should be true")
    }

}