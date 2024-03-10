package bookmark.data.service.find.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FindBookmarksRequest(
    @SerialName("collection")
    val collection: String,
    @SerialName("database")
    val database: String,
    @SerialName("dataSource")
    val dataSource: String
)