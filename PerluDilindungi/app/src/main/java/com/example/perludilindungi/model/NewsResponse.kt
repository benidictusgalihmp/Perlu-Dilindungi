package com.example.perludilindungi.model

data class NewsResponse(
    val count_total: Int,
    val message: String,
    val results: List<News>,
    val success: Boolean
)