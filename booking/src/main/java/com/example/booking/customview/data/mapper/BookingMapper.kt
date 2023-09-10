package com.example.booking.customview.data.mapper

import com.example.booking.customview.data.dto.BookingResponse
import com.example.booking.customview.domain.model.BookingModel
import com.example.core.base.BaseMapper

object BookingMapper : BaseMapper<BookingResponse?, BookingModel?>{
    override fun BookingResponse?.map(): BookingModel? {
        return this?.let {
            BookingModel(
                arrival_country = it.arrival_country,
                departure = it.departure,
                fuel_charge =it.fuel_charge,
                horating = it.horating,
                hotel_adress = it.hotel_adress,
                hotel_name = it.hotel_name,
                id = it.id,
                number_of_nights = it.number_of_nights,
                nutrition = it.nutrition,
                rating_name = it.rating_name,
                room = it.room,
                service_charge = it.service_charge,
                tour_date_start = it.tour_date_start,
                tour_date_stop = it.tour_date_stop,
                tour_price = it.tour_price
            )
        }
    }
}