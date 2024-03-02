package starter.presentation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test


class StarterScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun isTopAppBarVisibleWithInsideContents() = runComposeUiTest {
        setContent {
            StarterScreen.Content()
        }
        onNodeWithTag("starter_topAppBar").isNotDisplayed()
        onNodeWithTag("starter_topAppBar_title").isNotDisplayed()
    }

    
}


// ./gradlew :feature-auth:connectedAndroidTest

// ./gradlew :shared:connectedAndroidTest