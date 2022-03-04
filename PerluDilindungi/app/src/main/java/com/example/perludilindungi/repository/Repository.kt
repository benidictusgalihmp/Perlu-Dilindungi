package com.example.perludilindungi.repository

import com.example.perludilindungi.api.RetrofitInstance
import com.example.perludilindungi.model.News
import com.example.perludilindungi.model.NewsResponse
import com.example.perludilindungi.model.Province.ProvinceResponse
import retrofit2.Response

class Repository {

    suspend fun getNews(): Response<NewsResponse> {
        return RetrofitInstance.api.getNews()
    }

    suspend fun getProvince(): Response<ProvinceResponse> {
        return RetrofitInstance.api.getProvince()
    }

}