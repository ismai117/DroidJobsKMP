package presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import bookmark.domain.repository.BookmarkRepository
import kotlinx.coroutines.launch
import UIState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bookmark.presentation.BookmarkEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BookmarkViewModel(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    var state by mutableStateOf(BookmarkState())

    init {
        getBookmarks()
    }

    fun onEvent(event: BookmarkEvent) {
        when (event) {
            is BookmarkEvent.INSERT -> {
                insert(
                    jobId = event.jobId,
                    jobTitle = event.jobTitle,
                    companyName = event.companyName,
                    companyLogo = event.companyLogo
                )
            }

            is BookmarkEvent.CLEAR -> {
                clearValues()
            }
        }
    }

     fun getBookmarks() {
         viewModelScope.launch(Dispatchers.Default) {
            bookmarkRepository.find().collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is UIState.Loading -> {
                            state = state.copy(
                                isFindBookmarksLoading = true,
                                statusFindBookmarks = false
                            )
                        }

                        is UIState.Success -> {
                            state = state.copy(
                                isFindBookmarksLoading = false,
                                statusFindBookmarks = true,
                                bookmarks = result.data.orEmpty()
                            )
                        }

                        is UIState.Error -> {
                            state = state.copy(
                                isFindBookmarksLoading = false,
                                statusFindBookmarks = false,
                                errorFindBookmarks = result.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun insert(
        jobId: String,
        jobTitle: String,
        companyName: String,
        companyLogo: String
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            bookmarkRepository.insert(
                jobId = jobId,
                jobTitle = jobTitle,
                companyName = companyName,
                companyLogo = companyLogo
            ).collect { result ->
                when (result) {
                    is UIState.Loading -> {
                        state = state.copy(
                            isInsertBookmarkLoading = true,
                            statusInsertBookmark = false
                        )
                    }

                    is UIState.Success -> {
                        state = state.copy(
                            isInsertBookmarkLoading = false,
                            statusInsertBookmark = true
                        )
                    }

                    is UIState.Error -> {
                        state = state.copy(
                            isInsertBookmarkLoading = false,
                            statusInsertBookmark = false,
                            errorInsertBookmark = result.message
                        )
                    }
                }
            }
        }
    }

    private fun clearValues() {
        state = state.copy(
            isFindBookmarksLoading = false,
            statusFindBookmarks = false,
            errorFindBookmarks = "",
            isInsertBookmarkLoading = false,
            statusInsertBookmark = false,
            errorInsertBookmark = ""
        )
    }

}