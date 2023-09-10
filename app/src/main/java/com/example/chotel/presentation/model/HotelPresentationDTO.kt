package com.example.chotel.presentation.model


data class HotelPresentationDTO(
    val name: String,
    val address: String,
    val rating: RatingPresentationDTO,
) : PresentationDTO

data class HotelDetailsPresentationDTO(
    val hotel: HotelPresentationDTO,
    val minimalPrice: String,
    val priceDescription: String,
    val images: List<String>,
    val description: String,
    val features: List<String>,
) : PresentationDTO