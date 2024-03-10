package bookmark.data.service.find.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FindBookmarksRequestResponseBody(
    @SerialName("documents")
    val documents: List<FindBookmarkRequestResponseBodyDocuments>
)

@Serializable
data class FindBookmarkRequestResponseBodyDocuments(
    @SerialName("_id")
    val _id: String,
    @SerialName("jobId")
    val jobId: String,
    @SerialName("jobTitle")
    val jobTitle: String,
    @SerialName("companyName")
    val companyName: String,
    @SerialName("companyLogo")
    val companyLogo: String
)
