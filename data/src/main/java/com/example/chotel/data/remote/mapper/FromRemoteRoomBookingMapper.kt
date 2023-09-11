package com.example.chotel.data.remote.mapper

import com.example.chotel.data.remote.HotelRoomBookingDetailsRemoteDTO
import com.example.chotel.domain.model.RoomBookingDetails
import org.koin.core.annotation.Factory

fun interface FromRemoteRoomBookingMapper :
    FromRemoteMapper<HotelRoomBookingDetailsRemoteDTO, RoomBookingDetails>

@Factory
class FromRemoteRoomBookingMapperImpl : FromRemoteRoomBookingMapper {
    override fun HotelRoomBookingDetailsRemoteDTO.map() =
        com.example.chotel.domain.model.RoomBookingDetails(
            id = id,
            arrivalCountry = arrivalCountry,
            departureCountry = departureCountry,
            hotel = com.example.chotel.domain.model.Hotel(
                name = hotelName,
                address = hotelAddress,
                rating = com.example.chotel.domain.model.Rating(
                    value = hotelRating,
                    description = hotelRatingDescription
                )
            ),
            nightsAmount = nightsAmount,
            nutrition = nutrition,
            room = room,
            tourStartDate = tourStartDate,
            tourEndDate = tourEndDate,
            prices = com.example.chotel.domain.model.RoomBookingDetails.Prices(
                fuel = priceForFuel,
                service = priceForService,
                tour = priceForTour
            ),
        )
}