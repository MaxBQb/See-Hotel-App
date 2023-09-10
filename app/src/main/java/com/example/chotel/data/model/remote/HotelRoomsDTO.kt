package com.example.chotel.data.model.remote

import com.google.gson.annotations.SerializedName

data class HotelRoomsRemoteDTO(
    val rooms: List<Room>
): RemoteDTO {
    data class Room(
        val id: Int,
        @SerializedName("image_urls") val images: List<String>,
        val name: String,
        @SerializedName("peculiarities") val features: List<String>,
        val price: Int,
        @SerializedName("price_per") val priceNote: String
    ): RemoteDTO
}