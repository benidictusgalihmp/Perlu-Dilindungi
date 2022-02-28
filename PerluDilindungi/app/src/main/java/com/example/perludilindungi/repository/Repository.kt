package com.example.perludilindungi.repository

import com.example.perludilindungi.api.RetrofitInstance
import com.example.perludilindungi.model.News

class Repository {

    suspend fun getNews(): News {
        return RetrofitInstance.api.getNews()
    }

}