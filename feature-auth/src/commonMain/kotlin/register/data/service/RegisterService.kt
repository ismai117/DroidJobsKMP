package register.data.service

import DroidJobsKMP.feature_auth.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.statement.HttpResponse
import io.ktor.client.HttpClient
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
import kotlinx.serialization.json.Json


class RegisterService {

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

    suspend fun register(email: String, password: String): HttpResponse {
         return client.post {
             url("https://eu-central-1.aws.realm.mongodb.com/api/client/v2.0/app/${BuildConfig.appId}/auth/providers/local-userpass/register")
             header(HttpHeaders.ContentType, ContentType.Application.Json)
             header(HttpHeaders.AccessControlAllowOrigin, '*')
             setBody(RegisterRequest(email, password))
         }
    }

}