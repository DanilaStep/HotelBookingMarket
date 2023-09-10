package com.example.booking.customview.data.repository

import com.example.booking.customview.data.api.BookingApi
import com.example.booking.customview.data.mapper.BookingMapper.map
import com.example.booking.customview.domain.model.BookingModel

class BookingRepositoryImpl(
    private val api:BookingApi
): BookingRepository {
    override suspend fun getBooking(): BookingModel? {
        return api.getBooking().body().map()
    }
}