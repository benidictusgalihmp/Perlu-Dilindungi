package com.example.perludilindungi.api

import com.example.perludilindungi.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val news_api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }

    val scan_api: ScanApi by lazy {
        retrofit.create(ScanApi::class.java)
    }
}