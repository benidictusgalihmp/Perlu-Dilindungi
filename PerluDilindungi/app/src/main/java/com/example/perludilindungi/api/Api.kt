package com.example.perludilindungi.api

import com.example.perludilindungi.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET( "api/get-news" )
    suspend fun getNews(): Response<NewsResponse>

}