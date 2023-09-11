package com.example.chotel.data.remote

import com.google.gson.annotations.SerializedName

data class HotelRoomBookingDetailsRemoteDTO(
    val id: Int,
    @SerializedName("arrival_country") val arrivalCountry: String,
    @SerializedName("departure") val departureCountry: String,
    @SerializedName("fuel_charge") val priceForFuel: Int,
    @SerializedName("horating") val hotelRating: Int,
    @SerializedName("hotel_adress") val hotelAddress: String,
    @SerializedName("hotel_name") val hotelName: String,
    @SerializedName("number_of_nights") val nightsAmount: Int,
    val nutrition: String,
    @SerializedName("rating_name") val hotelRatingDescription: String,
    val room: String,
    @SerializedName("service_charge") val priceForService: Int,
    @SerializedName("tour_date_start") val tourStartDate: String,
    @SerializedName("tour_date_stop") val tourEndDate: String,
    @SerializedName("tour_price") val priceForTour: Int
): RemoteDTO