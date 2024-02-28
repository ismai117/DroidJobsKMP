package register.presentation


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import register.data.fake.FakeRegisterRepositoryImpl
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterScreenModelTest {

    private lateinit var screenModel: RegisterScreenModel

    @BeforeTest
    fun setUp(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        screenModel = RegisterScreenModel(
           registerRepository = FakeRegisterRepositoryImpl()
        )
    }

    @AfterTest
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun blankEmailErrorShown(){
        screenModel.onEvent(RegisterEvent.EMAIL(""))
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(
            "Email can't be blank!",
            screenModel.state.emailError,
            "'Email can't be blank!' error message should be shown"
        )
    }

    @Test
    fun invalidEmailErrorShown(){
        screenModel.onEvent(RegisterEvent.EMAIL("ishyy17gmail.com"))
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(
            "Email is not valid!",
            screenModel.state.emailError,
            "'Email is not valid!' error message should be shown"
        )
    }

    @Test
    fun blankPasswordErrorShown(){
        screenModel.onEvent(RegisterEvent.PASSWORD(""))
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(
            "Password cannot be blank!",
            screenModel.state.passwordError,
            "'Password cannot be blank!' error message should be shown"
        )
    }

    @Test
    fun shortPasswordErrorShown(){
        screenModel.onEvent(RegisterEvent.PASSWORD("testing"))
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(
            "Password needs at least 8 characters",
            screenModel.state.passwordError,
            "'Password needs at least 8 characters' error message should be shown"
        )
    }

    @Test
    fun weakPasswordErrorShown(){
        screenModel.onEvent(RegisterEvent.PASSWORD("testingPassword"))
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(
            "Password needs at least one letter and digit",
            screenModel.state.passwordError,
            "'Password needs at least one letter and digit' error message should be shown"
        )
    }

    @Test
    fun passwordMismatchErrorShown(){
        screenModel.onEvent(RegisterEvent.PASSWORD("testingPassword1"))
        screenModel.onEvent(RegisterEvent.CONFIRM_PASSWORD("testingPassword2"))
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(
            "Both passwords do not match!",
            screenModel.state.confirmPasswordError,
            "'Both passwords do not match!' error message should be shown"
        )
    }

    @Test
    fun checkRegisterIsLoading() = runTest{
        screenModel.onEvent(RegisterEvent.EMAIL("ismail1973@gmail.com"))
        screenModel.onEvent(RegisterEvent.PASSWORD("testing1234"))
        screenModel.onEvent(RegisterEvent.CONFIRM_PASSWORD("testing1234"))
        assertEquals( true, screenModel.state.emailError?.isBlank(), screenModel.state.emailError)
        assertEquals( true, screenModel.state.passwordError?.isBlank(), screenModel.state.passwordError)
        assertEquals( true, screenModel.state.confirmPasswordError?.isBlank(),screenModel.state.confirmPasswordError)
        screenModel.onEvent(RegisterEvent.SUBMIT)
        assertEquals(true, screenModel.state.isLoading, "isLoading should be true")
    }

}