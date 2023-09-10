package com.example.booking.customview.domain.interactor

import com.example.booking.customview.data.repository.BookingRepository
import com.example.booking.customview.domain.model.BookingModel

class BookingInteractorImpl(
    private val repository: BookingRepository
) : BookingInteractor {
    override suspend fun getBooking(): BookingModel? {
        return repository.getBooking()
    }
}