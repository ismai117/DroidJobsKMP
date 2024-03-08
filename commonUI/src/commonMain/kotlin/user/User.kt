package user

data class User(
    val access_token: String,
    val refresh_token: String,
    val user_id: String,
    val device_id: String
)
