package com.example.perludilindungi.api.controller

import com.example.perludilindungi.api.InterfaceRetroFit
import com.example.perludilindungi.model.City.CityResponse
import com.example.perludilindungi.ui.faskes.FaskesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityController(faskesViewModel: FaskesViewModel): Callback<CityResponse?> {
    private var viewModel: FaskesViewModel = faskesViewModel

    fun start(keyProvince: String) {
        viewModel.citiesFetch.value = true
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(InterfaceRetroFit.BASE_URL_PROVINCE)
            .build()
        val service: InterfaceRetroFit = retrofit.create(InterfaceRetroFit::class.java)
        service.getCities(keyProvince)?.enqueue(this)
    }

    override fun onResponse(call: Call<CityResponse?>, response: Response<CityResponse?>) {
        if (response.isSuccessful){
            val cities = response.body()
            if (cities != null) {
                viewModel.cities.value = cities.results
            }
        }
        viewModel.citiesFetch.value = false
    }

    override fun onFailure(call: Call<CityResponse?>, t: Throwable) {
        viewModel.citiesFetch.value = false
    }
}