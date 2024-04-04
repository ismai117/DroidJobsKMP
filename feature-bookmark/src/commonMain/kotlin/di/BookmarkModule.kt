package di

import bookmark.data.service.BookmarkService
import bookmark.data.service.BookmarkServiceImpl
import data.repository.BookmarkRepositoryImpl
import bookmark.domain.repository.BookmarkRepository
import org.koin.dsl.module
import presentation.BookmarkViewModel


val bookmarkModule = module {
    single<BookmarkService> { BookmarkServiceImpl() }
    single<BookmarkRepository> { BookmarkRepositoryImpl(get()) }
    factory { BookmarkViewModel(get()) }
}
