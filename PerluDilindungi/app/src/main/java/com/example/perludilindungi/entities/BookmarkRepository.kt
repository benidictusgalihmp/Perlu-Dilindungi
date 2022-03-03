package com.example.perludilindungi.entities

import androidx.lifecycle.LiveData

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {

    val getMinifiedData: LiveData<List<BookmarkDisplayMini>> = bookmarkDao.getMinifiedData()

    fun insertBookmark(bookmark: Bookmark) {
        bookmarkDao.insertBookmark(bookmark)
    }
}