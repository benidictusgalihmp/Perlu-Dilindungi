package com.example.perludilindungi.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkDetail(
    @PrimaryKey
    val bookmarkDetailId: Int,
    val kode: String,
    val batch: String,
    val divaksin: Int,
    val divaksin_1: Int,
    val divaksin_2: Int,
    val batal_vaksin: Int,
    val batal_vaksin_1: Int,
    val batal_vaksin_2: Int,
    val pending_vaksin: Int,
    val pending_vaksin_1: Int,
    val pending_vaksin_2: Int,
    val tanggal: String
)
