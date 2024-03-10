package bookmark.data.service.insert.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class InsertBookmarkRequestResponseBody(
    @SerialName("insertedId")
    val insertedId: String,
)