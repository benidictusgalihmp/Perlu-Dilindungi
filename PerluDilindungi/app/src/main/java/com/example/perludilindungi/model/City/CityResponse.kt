package com.example.perludilindungi.model.City

data class CityResponse(
    val curr_val: String,
    val message: String,
    val results: ArrayList<CityData>? = null
)
