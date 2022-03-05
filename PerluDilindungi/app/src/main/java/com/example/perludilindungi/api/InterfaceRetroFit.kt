package com.example.perludilindungi.api

import com.example.perludilindungi.model.City.CityResponse
import com.example.perludilindungi.model.Faskes.FaskesResponse
import com.example.perludilindungi.model.Province.ProvinceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceRetroFit {
    @GET("get-province")
    fun getProvincies(): Call<ProvinceResponse?>?

    @GET("get-city")
    fun getCities(@Query("keyProvince") start_id: String) : Call<CityResponse?>?

    @GET("get-faskes-vaksinasi")
    fun getFaskeses(@Query("province") province: String, @Query("city") city: String) : Call<FaskesResponse>?

    companion object{
        const val BASE_URL_PROVINCE = "https://perludilindungi.herokuapp.com/api/"
        const val BASE_URL_FASKES = "https://perludilindungi.herokuapp.com/api/"
    }
}