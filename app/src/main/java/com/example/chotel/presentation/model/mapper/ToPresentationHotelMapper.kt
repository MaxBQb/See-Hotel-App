package com.example.chotel.presentation.model.mapper

import com.example.chotel.domain.model.Hotel
import com.example.chotel.domain.model.HotelDetails
import com.example.chotel.domain.model.Rating
import com.example.chotel.domain.model.mapper.map
import com.example.chotel.presentation.model.HotelDetailsPresentationDTO
import com.example.chotel.presentation.model.HotelPresentationDTO
import com.example.chotel.presentation.model.RatingPresentationDTO
import com.example.chotel.presentation.utils.Rub
import org.koin.core.annotation.Factory

fun interface ToPresentationHotelDetailsMapper : ToPresentationMapper<HotelDetails, HotelDetailsPresentationDTO>
fun interface ToPresentationHotelMapper : ToPresentationMapper<Hotel, HotelPresentationDTO>
fun interface ToPresentationRatingMapper : ToPresentationMapper<Rating, RatingPresentationDTO>

@Factory
class ToPresentationRatingMapperImpl: ToPresentationRatingMapper {
    override fun Rating.map() = RatingPresentationDTO(
        "$value $description"
    )
}

@Factory
class ToPresentationHotelMapperImpl(
    private val ratingMapper: ToPresentationRatingMapper
): ToPresentationHotelMapper {
    override fun Hotel.map() = HotelPresentationDTO(
        name = name,
        address = address,
        rating = ratingMapper.map(rating)
    )
}

@Factory
class ToPresentationHotelDetailsMapperImpl(
    private val hotelMapper: ToPresentationHotelMapper
): ToPresentationHotelDetailsMapper {
    override fun HotelDetails.map() = HotelDetailsPresentationDTO(
        hotel = hotelMapper.map(hotel),
        minimalPrice = minimalPrice.Rub,
        priceDescription = priceDescription,
        images = images,
        description = description,
        features = features
    )
}