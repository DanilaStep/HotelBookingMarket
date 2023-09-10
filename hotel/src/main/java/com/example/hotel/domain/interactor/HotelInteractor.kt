package com.example.hotel.domain.interactor

import com.example.hotel.domain.model.HotelModel

interface HotelInteractor {
    suspend fun getHotel(): HotelModel?
}