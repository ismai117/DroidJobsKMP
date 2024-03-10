package bookmark.data.service

import bookmark.data.service.find.response.FindBookmarksResponse
import bookmark.data.service.insert.response.InsertBookmarkResponse


interface BookmarkService {
    suspend fun find(): FindBookmarksResponse
    suspend fun insert(
        jobId: String,
        jobTitle: String,
        companyName: String,
        companyLogo: String
    ): InsertBookmarkResponse
}