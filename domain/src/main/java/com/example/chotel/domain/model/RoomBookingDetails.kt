package com.example.chotel.domain.model

data class RoomBookingDetails(
    val id: Int,
    val arrivalCountry: String,
    val departureCountry: String,
    val hotel: Hotel,
    val nightsAmount: Int,
    val nutrition: String,
    val room: String,
    val tourStartDate: String,
    val tourEndDate: String,
    val prices: Prices
): DomainModel {
    data class Prices(
        val fuel: Int,
        val service: Int,
        val tour: Int
    ): DomainModel {
        val total = fuel + service + tour
    }
}