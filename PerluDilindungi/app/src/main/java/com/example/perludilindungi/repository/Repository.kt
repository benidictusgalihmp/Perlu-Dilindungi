package com.example.perludilindungi.repository

import com.example.perludilindungi.api.RetrofitInstance
import com.example.perludilindungi.model.NewsResponse
import retrofit2.Response

class Repository {

    suspend fun getNews(): Response<NewsResponse> {
        return RetrofitInstance.api.getNews()
    }

}