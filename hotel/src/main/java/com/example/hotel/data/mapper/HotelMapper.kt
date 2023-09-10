package com.example.hotel.data.mapper

import com.example.core.base.BaseMapper
import com.example.hotel.data.dto.AboutTheHotelDto
import com.example.hotel.data.dto.HotelResponse
import com.example.hotel.domain.model.AboutHotelItem
import com.example.hotel.domain.model.HotelModel

object HotelMapper : BaseMapper<HotelResponse?, HotelModel?> {
    override fun HotelResponse?.map(): HotelModel? {
        return this?.let {
            HotelModel(
                aboutHotelItem = mapAboutHotelItem(it.about_the_hotel),
                adress = it.adress,
                id = it.id,
                imageUrls = it.image_urls,
                minimalPrice = it.minimal_price,
                name = it.name,
                priceForIt = it.price_for_it,
                rating = it.rating,
                ratingName = it.rating_name


            )
        }
    }

    private fun mapAboutHotelItem(data: AboutTheHotelDto): AboutHotelItem =
                    AboutHotelItem(
                        description = data.description,
                        peculiarities = data.peculiarities
                    )


}
