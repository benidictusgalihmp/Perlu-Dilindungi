package com.example.perludilindungi.ui.faskes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.model.Province.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch

class FaskesViewModel(private val repository: Repository) : ViewModel() {
    val provResponse : MutableLiveData<ProvinceResponse?> = MutableLiveData()

    fun getProvinces() {
        viewModelScope.launch {
            val response = repository.getProvince()

            // handling if response null
            if (response.isSuccessful) {
                provResponse.value = response.body()
            } else {
                provResponse.value = null
            }
        }
    }
}