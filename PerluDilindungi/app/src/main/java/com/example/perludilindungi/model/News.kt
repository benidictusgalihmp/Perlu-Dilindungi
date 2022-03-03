package com.example.perludilindungi.model

data class News(
    val description: Description,
    val enclosure: Enclosure,
    val guid: String,
    val link: List<String>,
    val pubDate: String,
    val title: String
)

data class Enclosure(
    val _length: String,
    val _type: String,
    val _url: String
)

data class Description(
    val __cdata: String
)