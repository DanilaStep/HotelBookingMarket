package com.example.booking.customview.data.repository

import com.example.booking.customview.domain.model.BookingModel

interface BookingRepository {
    suspend fun getBooking(): BookingModel?
}