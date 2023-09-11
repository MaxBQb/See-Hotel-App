package com.example.chotel.domain.model

data class Room(
    val id: Int,
    val images: List<String>,
    val name: String,
    val features: List<String>,
    val price: Int,
    val priceNote: String
): DomainModel
