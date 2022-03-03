package com.example.perludilindungi.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.perludilindungi.entities.relations.BookmarkDetailAndBookmark

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmark: Bookmark)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmarkDetail(bookmarkDetail: BookmarkDetail)

    @Query("SELECT nama, alamat FROM bookmark")
    fun getMinifiedData(): LiveData<List<BookmarkDisplayMini>>
}