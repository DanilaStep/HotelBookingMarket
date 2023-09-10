package com.example.hotel.data.repository

import com.example.hotel.data.api.HotelApi
import com.example.hotel.data.mapper.HotelMapper.map
import com.example.hotel.domain.model.HotelModel

class HotelRepositoryImpl (
    private val api: HotelApi
        ): HotelRepository{
    override suspend fun getHotel(): HotelModel? {
        return api.getHotel().body().map()
    }

}