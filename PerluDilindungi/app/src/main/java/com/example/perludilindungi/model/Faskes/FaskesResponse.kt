package com.example.perludilindungi.model.Faskes

data class FaskesResponse(
    val success: Boolean,
    val message: String,
    val count_total: Int,
    val results: ArrayList<FaskesData>? = null
)
