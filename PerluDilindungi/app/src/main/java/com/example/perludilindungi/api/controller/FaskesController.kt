package com.example.perludilindungi.api.controller

import com.example.perludilindungi.api.InterfaceRetroFit
import com.example.perludilindungi.model.Faskes.FaskesResponse
import com.example.perludilindungi.ui.faskes.FaskesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FaskesController(faskesViewModel: FaskesViewModel): Callback<FaskesResponse?> {
    private var viewModel: FaskesViewModel = faskesViewModel

    fun start(province: String, city: String) {
        viewModel.faskesesFetch.value = true
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(InterfaceRetroFit.BASE_URL_FASKES)
            .build()
        val service: InterfaceRetroFit = retrofit.create(InterfaceRetroFit::class.java)
        service.getFaskeses(province, city)?.enqueue(this)
    }

    override fun onResponse(call: Call<FaskesResponse?>, response: Response<FaskesResponse?>) {
        if (response.isSuccessful){
            val faskeses = response.body()
            if (faskeses != null) {
                viewModel.faskeses.value = faskeses.results
            }
        }
        viewModel.faskesesFetch.value = false
    }

    override fun onFailure(call: Call<FaskesResponse?>, t: Throwable) {
        viewModel.faskesesFetch.value = false
    }
}