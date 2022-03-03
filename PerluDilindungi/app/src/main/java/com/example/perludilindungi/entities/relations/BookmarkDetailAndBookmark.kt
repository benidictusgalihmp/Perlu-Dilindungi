package com.example.perludilindungi.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.perludilindungi.entities.Bookmark
import com.example.perludilindungi.entities.BookmarkDetail

data class BookmarkDetailAndBookmark(
    @Embedded val bookmarkDetail: BookmarkDetail,
    @Relation(
        parentColumn = "bookmarkDetailId",
        entityColumn = "bookmarkDetailId"
    )
    val bookmark: Bookmark
)
