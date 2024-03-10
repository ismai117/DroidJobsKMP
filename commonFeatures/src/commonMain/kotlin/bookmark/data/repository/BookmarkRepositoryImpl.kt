package bookmark.data.repository

import bookmark.data.service.BookmarkService
import bookmark.domain.repository.BookmarkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import UIState
import bookmark.domain.model.Bookmark

class BookmarkRepositoryImpl(
    private val bookmarkService: BookmarkService
) : BookmarkRepository {

    override suspend fun find(): Flow<UIState<List<Bookmark>>> = callbackFlow {
        runCatching {
            trySend(UIState.Loading())
            bookmarkService.find()
        }.onSuccess { response ->
            when(response.status){
                200 -> {
                    trySend(UIState.Success(
                        response.body.documents.map { item ->
                            Bookmark(
                                _id = item._id,
                                jobID = item.jobId,
                                jobTitle = item.jobTitle,
                                companyName = item.companyName,
                                companyLogo = item.companyLogo
                            )
                        }
                    ))
                }
                401 -> {
                    trySend(UIState.Error("Invalid"))
                }
                else -> {
                    trySend(UIState.Error("Unexpected response code: ${response.status}"))
                }
            }
        }.onFailure { e ->
            trySend(UIState.Error("Unexpected Error!"))
            e.printStackTrace()
        }
        awaitClose {
            close()
        }
    }

    override suspend fun insert(
        jobId: String,
        jobTitle: String,
        companyName: String,
        companyLogo: String
    ): Flow<UIState<Unit>> = callbackFlow {
        runCatching {
            trySend(UIState.Loading())
            bookmarkService.insert(
                jobId = jobId,
                jobTitle = jobTitle,
                companyName = companyName,
                companyLogo = companyLogo
            )
        }.onSuccess { response ->
            when(response.status){
                201 -> {
                    trySend(UIState.Success(null))
                }
                401 -> {
                    trySend(UIState.Error("Invalid"))
                }
                else -> {
                    trySend(UIState.Error("Unexpected response code: ${response.status}"))
                }
            }
        }.onFailure { e ->
            trySend(UIState.Error("Unexpected Error!"))
            e.printStackTrace()
        }
        awaitClose {
            close()
        }
    }


}