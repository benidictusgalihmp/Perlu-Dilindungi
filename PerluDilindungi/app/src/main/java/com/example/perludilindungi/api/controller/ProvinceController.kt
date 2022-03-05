package com.example.perludilindungi.api.controller

import com.example.perludilindungi.api.InterfaceRetroFit
import com.example.perludilindungi.model.Province.ProvinceResponse
import com.example.perludilindungi.ui.faskes.FaskesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinceController(faskesViewModel: FaskesViewModel): Callback<ProvinceResponse?> {
    private var viewModel: FaskesViewModel = faskesViewModel

    fun start() {
        viewModel.provincesFetch.value = true
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(InterfaceRetroFit.BASE_URL_PROVINCE)
            .build()
        val service: InterfaceRetroFit = retrofit.create(InterfaceRetroFit::class.java)
        service.getProvincies()?.enqueue(this)
    }

    override fun onResponse(call: Call<ProvinceResponse?>, response: Response<ProvinceResponse?>) {
        if (response.isSuccessful){
            val provinces = response.body()
            if (provinces != null) {
                viewModel.provinces.value = provinces.results
            }
        }
        viewModel.provincesFetch.value = false
    }

    override fun onFailure(call: Call<ProvinceResponse?>, t: Throwable) {
        viewModel.provincesFetch.value = false
    }
}