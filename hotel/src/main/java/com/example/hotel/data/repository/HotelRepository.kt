package com.example.hotel.data.repository

import com.example.hotel.domain.model.HotelModel

interface HotelRepository {
    suspend fun getHotel(): HotelModel?
}