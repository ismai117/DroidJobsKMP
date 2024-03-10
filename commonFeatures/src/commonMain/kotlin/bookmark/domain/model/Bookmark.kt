package bookmark.domain.model


data class Bookmark(
    val _id: String,
    val jobID: String,
    val jobTitle: String,
    val companyName: String,
    val companyLogo: String
)
