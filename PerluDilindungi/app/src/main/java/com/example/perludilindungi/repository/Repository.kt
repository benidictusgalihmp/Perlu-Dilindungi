package com.example.perludilindungi.repository

import com.example.perludilindungi.api.RetrofitInstance
import com.example.perludilindungi.model.NewsResponse
import com.example.perludilindungi.model.ScanPostBody
import com.example.perludilindungi.model.ScanPostResponse
import retrofit2.Response

class Repository {

    suspend fun getNews(): Response<NewsResponse> {
        return RetrofitInstance.news_api.getNews()
    }

    suspend fun scanPost(post: ScanPostBody): Response<ScanPostResponse> {
        return RetrofitInstance.scan_api.scanPost(post)
    }

}