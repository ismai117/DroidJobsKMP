package user


import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class UserState {

    val settings: Settings = Settings()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    init {
        fun isUserLoggedIn() {
            _isUserLoggedIn.value = settings.getBoolean("isUserLoggedIn", false)
        }
        isUserLoggedIn()
    }

    fun setLoggedIn(state: Boolean) {
        settings.set("isUserLoggedIn", state)
    }

}

