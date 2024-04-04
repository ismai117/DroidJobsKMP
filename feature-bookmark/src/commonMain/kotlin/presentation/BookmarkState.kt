package presentation

import bookmark.domain.model.Bookmark


data class BookmarkState(

    val isFindBookmarksLoading: Boolean = false,
    val statusFindBookmarks: Boolean= false,
    val errorFindBookmarks: String = "",

    val isInsertBookmarkLoading: Boolean = false,
    val statusInsertBookmark: Boolean= false,
    val errorInsertBookmark: String = "",

    val bookmarks: List<Bookmark> = emptyList()

)