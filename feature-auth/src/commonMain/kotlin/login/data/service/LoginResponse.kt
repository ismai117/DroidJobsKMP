package login.data.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class LoginResponse(
    val status: Int,
    val body: LoginBody
)

data class LoginBody(
    val access_token: String,
    val refresh_token: String,
    val user_id: String,
    val device_id: String
)
