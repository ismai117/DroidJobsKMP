package login.data.service

import DroidJobsKMP.feature_auth.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class LoginServiceImpl : LoginService {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging){
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v("HTTP Client", null, message)
                }
            }
            level = LogLevel.HEADERS
        }
    }.also { Napier.base(DebugAntilog()) }

    override suspend fun login(email: String, password: String): LoginResponse {
        val response = client.post {
            url("https://eu-central-1.aws.realm.mongodb.com/api/client/v2.0/app/${BuildConfig.appId}/auth/providers/local-userpass/login")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.AccessControlAllowOrigin, '*')
            setBody(LoginRequest(email, password))
        }

        return if (response.status.value in 200..299) {
            val body = response.body<Body>()
            LoginResponse(
                status = response.status.value,
                body = LoginBody(
                    access_token = body.access_token,
                    refresh_token = body.refresh_token,
                    user_id = body.user_id,
                    device_id = body.device_id
                )
            )
        }else {
            LoginResponse(
                status = response.status.value,
                body = LoginBody(
                    access_token = "",
                    refresh_token = "",
                    user_id = "",
                    device_id = ""
                )
            )
        }
    }

}


@Serializable
data class Body(
    @SerialName("access_token")
    val access_token: String,
    @SerialName("refresh_token")
    val refresh_token: String,
    @SerialName("user_id")
    val user_id: String,
    @SerialName("device_id")
    val device_id: String
)

