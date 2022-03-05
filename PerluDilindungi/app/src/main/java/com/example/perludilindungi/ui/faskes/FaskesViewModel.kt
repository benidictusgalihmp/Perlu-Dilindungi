package com.example.perludilindungi.ui.faskes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perludilindungi.model.City.CityData
import com.example.perludilindungi.model.Faskes.FaskesData
import com.example.perludilindungi.model.Province.ProvinceData
import com.example.perludilindungi.repository.Repository

class FaskesViewModel(private val repository: Repository) : ViewModel() {
//    val provResponse : MutableLiveData<ProvinceResponse?> = MutableLiveData()

    val provinceSelect: MutableLiveData<Int?> by lazy {
        MutableLiveData<Int?>()
    }
    val citySelect: MutableLiveData<Int?> by lazy {
        MutableLiveData<Int?>()
    }

    val provincesFetch: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val provinces: MutableLiveData<ArrayList<ProvinceData>?> by lazy {
        MutableLiveData<ArrayList<ProvinceData>?>()
    }

    val citiesFetch: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val cities: MutableLiveData<ArrayList<CityData>?> by lazy {
        MutableLiveData<ArrayList<CityData>?>()
    }

    val faskesesFetch: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val faskeses : MutableLiveData<ArrayList<FaskesData>?> by lazy {
        MutableLiveData<ArrayList<FaskesData>?>()
    }

}