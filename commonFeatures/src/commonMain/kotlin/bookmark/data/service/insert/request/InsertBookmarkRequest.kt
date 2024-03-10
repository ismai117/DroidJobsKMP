package bookmark.data.service.insert.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InsertBookmarkRequest(
    @SerialName("collection")
    val collection: String,
    @SerialName("database")
    val database: String,
    @SerialName("dataSource")
    val dataSource: String,
    @SerialName("document")
    val document: InsertBookmarkRequestDocument
)

@Serializable
data class InsertBookmarkRequestDocument(
    @SerialName("jobId")
    val jobId: String,
    @SerialName("jobTitle")
    val jobTitle: String,
    @SerialName("companyName")
    val companyName: String,
    @SerialName("companyLogo")
    val companyLogo: String
)
