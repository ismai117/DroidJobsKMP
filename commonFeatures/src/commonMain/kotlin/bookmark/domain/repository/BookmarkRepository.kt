package bookmark.domain.repository

import kotlinx.coroutines.flow.Flow
import UIState
import bookmark.domain.model.Bookmark

interface BookmarkRepository {
    suspend fun find(): Flow<UIState<List<Bookmark>>>
    suspend fun insert(
        jobId: String,
        jobTitle: String,
        companyName: String,
        companyLogo: String
    ): Flow<UIState<Unit>>
}