package bookmark.data.service.insert.response

data class InsertBookmarkResponse(
    val status: Int,
    val body: InsertBookmarkResponseBody
)

data class InsertBookmarkResponseBody(
    val insertedId: String
)
