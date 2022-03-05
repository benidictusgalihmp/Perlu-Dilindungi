package com.example.perludilindungi

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.model.NewsResponse
import com.example.perludilindungi.model.ScanPostBody
import com.example.perludilindungi.model.ScanPostResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val newsResponse: MutableLiveData<Response<NewsResponse>> = MutableLiveData()
    val scanResponse: MutableLiveData<Response<ScanPostResponse>> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch {
            val response = repository.getNews()
            newsResponse.value = response
        }
    }

    fun scanPost(post: ScanPostBody) {
        viewModelScope.launch {
            val response = repository.scanPost(post)
            scanResponse.value = response
        }
    }

}