package com.example.chotel.data.remote.mapper

import com.example.chotel.data.remote.HotelRemoteDTO
import com.example.chotel.domain.model.HotelDetails
import org.koin.core.annotation.Factory

fun interface FromRemoteHotelMapper : FromRemoteMapper<HotelRemoteDTO, HotelDetails>

@Factory
class FromRemoteHotelMapperImpl: FromRemoteHotelMapper {
    override fun HotelRemoteDTO.map() = com.example.chotel.domain.model.HotelDetails(
        id = id,
        hotel = com.example.chotel.domain.model.Hotel(
            name = name,
            address = address,
            rating = com.example.chotel.domain.model.Rating(
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