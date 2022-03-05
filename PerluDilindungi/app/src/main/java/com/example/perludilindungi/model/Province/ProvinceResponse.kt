package com.example.perludilindungi.model.Province

data class ProvinceResponse(
    val curr_val: String,
    val message: String,
    val results: ArrayList<ProvinceData>? = null
)
