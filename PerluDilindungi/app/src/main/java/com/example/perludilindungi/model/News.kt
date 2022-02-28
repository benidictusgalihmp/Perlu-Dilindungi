package com.example.perludilindungi.model

data class News(
    val count_total: Int,
    val message: String,
    val results: List<Result>,
    val success: Boolean
)