package com.example.perludilindungi.model

data class ScanPostResponse(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)