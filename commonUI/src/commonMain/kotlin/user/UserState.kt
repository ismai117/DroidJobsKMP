package user


import androidx.compose.runtime.mutableStateOf
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class UserState {

    val settings: Settings = Settings()

    val isUserLoggedIn = mutableStateOf(false)

    fun getUserState() {
        isUserLoggedIn.value = settings.getBoolean("isUserLoggedIn", false)
    }

    fun setLoggedIn(state: Boolean) {
        settings.set("isUserLoggedIn", state)
    }

}

