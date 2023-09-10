package com.example.chotel.presentation.model

data class RoomBookingDetailsPresentationDTO(
    val arrivalCountry: String,
    val departureCountry: String,
    val hotel: HotelPresentationDTO,
    val nightsAmount: Int,
    val nutrition: String,
    val room: String,
    val tourDatesSpan: String,
    val prices: Prices,
): PresentationDTO {
    data class Prices(
        val fuel: String,
        val service: String,
        val tour: String,
        val total: String,
    ): PresentationDTO
}