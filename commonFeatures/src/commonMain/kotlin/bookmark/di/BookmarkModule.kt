package di

import bookmark.data.service.BookmarkService
import bookmark.data.service.BookmarkServiceImpl
import bookmark.data.repository.BookmarkRepositoryImpl
import bookmark.domain.repository.BookmarkRepository

object BookmarkModule {
    private val bookmarkService: BookmarkService by lazy {
        BookmarkServiceImpl()
    }
    val bookmarkRepository: BookmarkRepository by lazy {
        BookmarkRepositoryImpl(bookmarkService = bookmarkService)
    }
}