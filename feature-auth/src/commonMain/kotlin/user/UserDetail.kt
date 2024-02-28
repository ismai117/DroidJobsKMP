package user

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class UserDetail {

    private val settings: Settings by lazy { Settings() }

    fun getUserDetail(): User {
        return User(
            access_token = settings.getString("accessToken", defaultValue = ""),
            refresh_token = settings.getString("refreshToken", defaultValue = ""),
            user_id = settings.getString("userId", defaultValue = ""),
            device_id = settings.getString("deviceId", defaultValue = ""),
        )
    }

    fun setUserDetail(
        accessToken: String,
        refreshToken: String,
        userId: String,
        deviceId: String
    ){
        settings.set("accessToken", accessToken)
        settings.set("refreshToken", refreshToken)
        settings.set("userId", userId)
        settings.set("deviceId", deviceId)
    }

}
