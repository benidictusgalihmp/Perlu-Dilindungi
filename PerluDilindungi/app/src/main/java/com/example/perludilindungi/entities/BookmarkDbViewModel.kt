package com.example.perludilindungi.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkDbViewModel(application: Application): AndroidViewModel(application) {

    val getMinifiedData: LiveData<List<BookmarkDisplayMini>>
    private val repository: BookmarkRepository

    init {
        val bookmarkDao = BookmarkDatabase.getDatabase(application).bookmarkDao()
        repository = BookmarkRepository(bookmarkDao)
        getMinifiedData = repository.getMinifiedData
    }

    fun insertBookmark(bookmark: Bookmark) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBookmark((bookmark))
        }
    }
}