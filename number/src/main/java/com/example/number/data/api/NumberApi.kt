package com.example.number.data.api

import com.example.number.data.dto.NumberResponse
import retrofit2.Response
import retrofit2.http.GET

interface NumberApi {
    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getNumber(): Response<NumberResponse>
}