package com.example.chotel.presentation.model

data class RoomPresentationDTO(
    val images: List<String>,
    val name: String,
    val features: List<String>,
    val price: String,
    val priceNote: String
): PresentationDTO
