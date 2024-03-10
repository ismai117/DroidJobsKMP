package bookmark.data.service

import DroidJobsKMP.commonFeatures.BuildConfig
import bookmark.data.service.find.request.FindBookmarksRequest
import bookmark.data.service.find.request.FindBookmarksRequestResponseBody
import bookmark.data.service.find.response.FindBookmarkResponseBodyDocuments
import bookmark.data.service.find.response.FindBookmarksResponse
import bookmark.data.service.find.response.FindBookmarksResponseBody
import bookmark.data.service.insert.request.InsertBookmarkRequest
import bookmark.data.service.insert.request.InsertBookmarkRequestDocument
import bookmark.data.service.insert.request.InsertBookmarkRequestResponseBody
import bookmark.data.service.insert.response.InsertBookmarkResponse
import bookmark.data.service.insert.response.InsertBookmarkResponseBody
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
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


class BookmarkServiceImpl : BookmarkService {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v("HTTP Client", null, message)
                }
            }
            level = LogLevel.HEADERS
        }
        install(Auth) {

        }
    }.also { Napier.base(DebugAntilog()) }

    override suspend fun find(): FindBookmarksResponse {
        val response = client.post {
            url("https://eu-west-2.aws.data.mongodb-api.com/app/${BuildConfig.dataId}/endpoint/data/v1/action/find")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.AccessControlAllowOrigin, '*')
            header("apiKey", BuildConfig.apiKey)
            setBody(
                FindBookmarksRequest(
                    collection = "bookmark",
                    database = "DroidJobsDB",
                    dataSource = "Cluster0"
                )
            )
        }
        return if (response.status.value in 200..299) {
            val body = response.body<FindBookmarksRequestResponseBody>()
            FindBookmarksResponse(
                status = response.status.value,
                body =  FindBookmarksResponseBody(
                    documents = body.documents.map {
                        FindBookmarkResponseBodyDocuments(
                            _id = it._id,
                            jobId = it.jobId,
                            jobTitle = it.jobTitle,
                            companyName = it.companyName,
                            companyLogo = it.companyLogo
                        )
                    }
                )
            )
        } else {
            FindBookmarksResponse(
                status = response.status.value,
                body = FindBookmarksResponseBody(
                    documents = emptyList()
                )
            )
        }
    }

    override suspend fun insert(
        jobId: String,
        jobTitle: String,
        companyName: String,
        companyLogo: String
    ): InsertBookmarkResponse {
        val response = client.post {
            url("https://eu-west-2.aws.data.mongodb-api.com/app/${BuildConfig.dataId}/endpoint/data/v1/action/insertOne")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.AccessControlAllowOrigin, '*')
            header("apiKey", BuildConfig.apiKey)
            setBody(
                InsertBookmarkRequest(
                    collection = "bookmark",
                    database = "DroidJobsDB",
                    dataSource = "Cluster0",
                    document = InsertBookmarkRequestDocument(
                        jobId = jobId,
                        jobTitle = jobTitle,
                        companyName = companyName,
                        companyLogo = companyLogo
                    )
                )
            )
        }
        return if (response.status.value in 200..299) {
            val body = response.body<InsertBookmarkRequestResponseBody>()
            InsertBookmarkResponse(
                status = response.status.value,
                body = InsertBookmarkResponseBody(
                    insertedId = body.insertedId
                )
            )
        } else {
            InsertBookmarkResponse(
                status = response.status.value,
                body = InsertBookmarkResponseBody(
                    insertedId = ""
                )
            )
        }
    }

}







