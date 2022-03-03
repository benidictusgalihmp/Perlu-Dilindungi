package com.example.perludilindungi.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @PrimaryKey(autoGenerate = false)
    val bookmarkId: Int,
    val bookmarkDetailId: Int,
    val kode: String,
    val nama: String,
    val kota: String,
    val provinsi: String,
    val alamat: String,
    val latitude: String,
    val longitude: String,
    val telp: String,
    val jenis_faskes: String,
    val kelas_rs: String,
    val status: String,
    val source_data: String,
)
