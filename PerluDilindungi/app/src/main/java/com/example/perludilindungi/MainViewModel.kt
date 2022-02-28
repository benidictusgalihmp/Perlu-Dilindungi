package com.example.perludilindungi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.model.News
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<News> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch {
            val response = repository.getNews()
            myResponse.value = response
        }
    }

}