package com.example.perludilindungi.api

import com.example.perludilindungi.model.News
import retrofit2.http.GET

interface Api {

    @GET( "api/get-news" )
    suspend fun getNews(): News

}