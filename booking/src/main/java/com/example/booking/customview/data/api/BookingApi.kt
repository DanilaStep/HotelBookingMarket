package com.example.booking.customview.data.api

import com.example.booking.customview.data.dto.BookingResponse
import retrofit2.Response
import retrofit2.http.GET

interface BookingApi {
    @GET ("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBooking(): Response<BookingResponse>
}