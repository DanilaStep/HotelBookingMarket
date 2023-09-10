package com.example.hotel.domain.interactor

import com.example.hotel.data.repository.HotelRepository
import com.example.hotel.domain.model.HotelModel

class HotelInteractorImpl(
    private val repository: HotelRepository
) : HotelInteractor {
    override suspend fun getHotel(): HotelModel? {
        return repository.getHotel()
    }
}