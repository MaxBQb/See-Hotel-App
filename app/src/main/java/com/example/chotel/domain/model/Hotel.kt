package com.example.chotel.domain.model


data class Hotel(
    val name: String,
    val address: String,
    val rating: Rating,
) : DomainModel

data class HotelDetails(
    val id: Int,
    val hotel: Hotel,
    val minimalPrice: Int,
    val priceDescription: String,
    val images: List<String>,
    val description: String,
    val features: List<String>,
) : DomainModel