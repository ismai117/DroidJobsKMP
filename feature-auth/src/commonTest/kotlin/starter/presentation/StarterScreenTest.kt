package starter.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlin.test.Test


class StarterScreenTest {


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun isTopAppBarVisible() = runComposeUiTest {
        setContent {
            StarterScreenContent(
                navigateToLoginScreen = {},
                navigateToRegisterScreen = {},
                containerWidth = 768.dp,
                containerWidthOnChange = {}
            )
        }
        onNodeWithTag("starter_topAppBar_title").isDisplayed()
        onNodeWithTag("starter_topAppBar_title").assertTextContains("DroidJobs")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun isTopAppBarVisibleWithInsideContentsAlongWithMenuBar() = runComposeUiTest {
        setContent {
            var containerWidth by remember { mutableStateOf(765.dp) }
            StarterScreenContent(
                navigateToLoginScreen = {},
                navigateToRegisterScreen = {},
                containerWidth = 765.dp,
                containerWidthOnChange = { containerWidth = it }
            )
        }
        onNodeWithTag("starter_topAppBar_title").isDisplayed()
        onNodeWithTag("starter_topAppBar_title").assertTextContains("DroidJobs")
        onNodeWithTag("starter_topAppBar_menu_iconBtn").assertIsDisplayed()
        onNodeWithTag("starter_topAppBar_signIn_textBtn").assertDoesNotExist()
        onNodeWithTag("starter_topAppBar_signUp_textBtn").assertDoesNotExist()
        onNodeWithTag("starter_topAppBar_theme_icon").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun isTopAppBarVisibleWithInsideContentsAlongWithoutMenuBar() = runComposeUiTest {
        setContent {
            var containerWidth by remember { mutableStateOf(770.dp) }
            StarterScreenContent(
                navigateToLoginScreen = {},
                navigateToRegisterScreen = {},
                containerWidth = 770.dp,
                containerWidthOnChange = { containerWidth = it }
            )
        }
        onNodeWithTag("starter_topAppBar_title").isDisplayed()
        onNodeWithTag("starter_topAppBar_title").assertTextContains("DroidJobs")
        onNodeWithTag("starter_topAppBar_menu_iconBtn").assertDoesNotExist()
        onNodeWithTag("starter_topAppBar_signIn_textBtn").isDisplayed()
        onNodeWithTag("starter_topAppBar_signUp_textBtn").isDisplayed()
        onNodeWithTag("starter_topAppBar_theme_icon").isDisplayed()
    }

}


// ./gradlew :feature-auth:connectedAndroidTest

// ./gradlew :shared:connectedAndroidTest