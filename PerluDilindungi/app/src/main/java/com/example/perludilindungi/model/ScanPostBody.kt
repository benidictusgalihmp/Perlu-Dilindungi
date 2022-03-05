package com.example.perludilindungi.model

data class ScanPostBody(
    val qrCode: String,
    val latitude: Int,
    val longitude: Int
)