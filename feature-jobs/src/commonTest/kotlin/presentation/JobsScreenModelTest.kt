package presentation

import di.JobsModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


@OptIn(ExperimentalCoroutinesApi::class)
class JobsScreenModelTest {

    private lateinit var screenModel: JobsScreenModeL

    @BeforeTest
    fun setUp(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        screenModel = JobsScreenModeL(
            jobsRepository = JobsModule.jobsRepository
        )
    }

    @AfterTest
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun testInitialState() {
        assertNotNull(screenModel.state)
        assertFalse(screenModel.searching.value)
        assertEquals("", screenModel.query.value)
        assertTrue(screenModel.jobs.value.isEmpty())
    }

}


