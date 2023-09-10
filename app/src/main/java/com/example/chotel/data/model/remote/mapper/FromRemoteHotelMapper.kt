package com.example.chotel.data.model.remote.mapper

import com.example.chotel.data.model.remote.HotelRemoteDTO
import com.example.chotel.domain.model.Hotel
import com.example.chotel.domain.model.HotelDetails
import com.example.chotel.domain.model.Rating
import org.koin.core.annotation.Factory

fun interface FromRemoteHotelMapper : FromRemoteMapper<HotelRemoteDTO, HotelDetails>

@Factory
class FromRemoteHotelMapperImpl: FromRemoteHotelMapper {
    override fun HotelRemoteDTO.map() = HotelDetails(
        id = id,
        hotel = Hotel(
            name = name,
            address = address,
            rating = Rating(
                value = rating,
                description = ratingDescription
            ),
        ),
        minimalPrice = minimalPrice,
        priceDescription = priceDescription,
        images = images,
        description = details.description,
        features = details.features
    )
}