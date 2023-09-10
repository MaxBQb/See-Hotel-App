package com.example.chotel.data.model.remote.mapper

import com.example.chotel.data.model.remote.HotelRoomBookingDetailsRemoteDTO
import com.example.chotel.domain.model.Hotel
import com.example.chotel.domain.model.Rating
import com.example.chotel.domain.model.RoomBookingDetails
import org.koin.core.annotation.Factory

fun interface FromRemoteRoomBookingMapper :
    FromRemoteMapper<HotelRoomBookingDetailsRemoteDTO, RoomBookingDetails>

@Factory
class FromRemoteRoomBookingMapperImpl : FromRemoteRoomBookingMapper {
    override fun HotelRoomBookingDetailsRemoteDTO.map() = RoomBookingDetails(
        id = id,
        arrivalCountry = arrivalCountry,
        departureCountry = departureCountry,
        hotel = Hotel(
            name = hotelName,
            address = hotelAddress,
            rating = Rating(
                value = hotelRating,
                description = hotelRatingDescription
            )
        ),
        nightsAmount = nightsAmount,
        nutrition = nutrition,
        room = room,
        tourStartDate = tourStartDate,
        tourEndDate = tourEndDate,
        prices = RoomBookingDetails.Prices(
            fuel = priceForFuel,
            service = priceForService,
            tour = priceForTour
        ),
    )
}