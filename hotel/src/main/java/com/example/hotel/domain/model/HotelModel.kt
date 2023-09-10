package com.example.hotel.domain.model

data class HotelModel (
    val aboutHotelItem: AboutHotelItem,
    val adress: String,
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)