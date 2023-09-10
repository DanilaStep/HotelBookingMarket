package com.example.number.data.mapper

import com.example.core.base.BaseMapper
import com.example.number.data.dto.NumberResponse
import com.example.number.data.dto.Room
import com.example.number.domain.model.NumberModel
import com.example.number.domain.model.RoomItem

object NumberMapper : BaseMapper<NumberResponse?, NumberModel?> {
    override fun NumberResponse?.map(): NumberModel? {
        return this?.let {
            NumberModel(
                rooms = mapRoomItem(it.rooms)
            )
        }
    }

    private fun mapRoomItem(list: List<Room>?): MutableList<RoomItem> =
        mutableListOf<RoomItem>().apply {
            list?.forEach {
                add(
                    RoomItem(
                        id = it.id,
                        image_urls = it.image_urls,
                        name = it.name,
                        peculiarities = it.peculiarities,
                        price = it.price,
                        price_per = it.price_per
                    )
                )
            }
        }
}