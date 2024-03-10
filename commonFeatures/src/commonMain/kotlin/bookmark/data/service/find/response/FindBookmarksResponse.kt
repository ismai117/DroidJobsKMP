package bookmark.data.service.find.response



data class FindBookmarksResponse(
    val status: Int,
    val body: FindBookmarksResponseBody
)

data class FindBookmarksResponseBody(
    val documents: List<FindBookmarkResponseBodyDocuments>
)
data class FindBookmarkResponseBodyDocuments(
    val _id: String,
    val jobId: String,
    val jobTitle: String,
    val companyName: String,
    val companyLogo: String
)
