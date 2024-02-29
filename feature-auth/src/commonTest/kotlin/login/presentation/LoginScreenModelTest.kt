package login.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import login.data.fake.FakeLoginRepositoryImpl
import login.data.fake.FakeLoginServiceImpl
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenModelTest {

    private lateinit var screenModel: LoginScreenModel

    private val testDispatcher = AppDispatchers(
        IO = StandardTestDispatcher()
    )

    @BeforeTest
    fun setUp(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        screenModel = LoginScreenModel(
            loginRepository = FakeLoginRepositoryImpl(
                fakeLoginServiceImpl = FakeLoginServiceImpl()
            )
        )
    }

    @AfterTest
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun blankEmailErrorShown(){
        screenModel.onEvent(LoginEvent.EMAIL(""))
        screenModel.onEvent(LoginEvent.SUBMIT)
        assertEquals(
            "Email can't be blank!",
            screenModel.state.emailError,
            "'Email can't be blank!' error message should be shown"
        )
    }

    @Test
    fun invalidEmailErrorShown(){
        screenModel.onEvent(LoginEvent.EMAIL("ishyy17gmail.com"))
        screenModel.onEvent(LoginEvent.SUBMIT)
        assertEquals(
            "Email is not valid!",
            screenModel.state.emailError,
            "'Email is not valid!' error message should be shown"
        )
    }

    @Test
    fun blankPasswordErrorShown(){
        screenModel.onEvent(LoginEvent.PASSWORD(""))
        screenModel.onEvent(LoginEvent.SUBMIT)
        assertEquals(
            "Password cannot be blank!",
            screenModel.state.passwordError,
            "'Password cannot be blank!' error message should be shown"
        )
    }

    @Test
    fun shortPasswordErrorShown(){
        screenModel.onEvent(LoginEvent.PASSWORD("testing"))
        screenModel.onEvent(LoginEvent.SUBMIT)
        assertEquals(
            "Password needs at least 8 characters",
            screenModel.state.passwordError,
            "'Password needs at least 8 characters' error message should be shown"
        )
    }

    @Test
    fun checkLoginIsLoading() = runTest{
        screenModel.onEvent(LoginEvent.EMAIL("ishyfaisal@gmail.com"))
        screenModel.onEvent(LoginEvent.PASSWORD("testing123"))
        assertEquals(true, screenModel.state.emailError?.isBlank(), screenModel.state.emailError)
        assertEquals(true, screenModel.state.passwordError?.isBlank(), screenModel.state.passwordError)
        assertEquals(false, screenModel.state.isLoading, "isLoading should be false")
        screenModel.onEvent(LoginEvent.SUBMIT)
        assertEquals(true, screenModel.state.isLoading, "isLoading should be true")
    }

    @Test
    fun checkLoginIsSuccess(){

    }

}