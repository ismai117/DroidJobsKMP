package bookmark.presentation

sealed interface BookmarkEvent {
    class INSERT(
        val jobId: String,
        val jobTitle: String,
        val companyName: String,
        val companyLogo: String
    ) : BookmarkEvent
    data object CLEAR : BookmarkEvent
}