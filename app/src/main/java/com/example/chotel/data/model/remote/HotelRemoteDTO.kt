package com.example.chotel.data.model.remote

import com.google.gson.annotations.SerializedName

data class HotelRemoteDTO(
    val id: Int,
    val name: String,
    @SerializedName("adress") val address: String,
    @SerializedName("minimal_price") val minimalPrice: Int,
    @SerializedName("price_for_it") val priceDescription: String,
    val rating: Int,
    @SerializedName("rating_name") val ratingDescription: String,
    @SerializedName("image_urls") val images: List<String>,
    @SerializedName("about_the_hotel") val details: Details,
) : RemoteDTO {
    data class Details(
        val description: String,
        @SerializedName("peculiarities") val features: List<String>,
    )
}