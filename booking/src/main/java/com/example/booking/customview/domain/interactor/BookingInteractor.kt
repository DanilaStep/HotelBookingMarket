package com.example.booking.customview.domain.interactor

import com.example.booking.customview.domain.model.BookingModel

interface BookingInteractor {
    suspend fun getBooking(): BookingModel?
}