package com.example.perludilindungi.api

import com.example.perludilindungi.model.ScanPostBody
import com.example.perludilindungi.model.ScanPostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ScanApi {

    @POST( "/check-in" )
    suspend fun scanPost(
        @Body post: ScanPostBody
    ): Response<ScanPostResponse>

}