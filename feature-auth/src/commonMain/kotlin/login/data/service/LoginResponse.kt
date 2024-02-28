package login.data.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val access_token: String,
    @SerialName("refresh_token")
    val refresh_token: String,
    @SerialName("user_id")
    val user_id: String,
    @SerialName("device_id")
    val device_id: String
)
