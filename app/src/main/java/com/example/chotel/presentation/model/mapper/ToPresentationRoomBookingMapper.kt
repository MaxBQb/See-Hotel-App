package com.example.chotel.presentation.model.mapper

import com.example.chotel.domain.model.RoomBookingDetails
import com.example.chotel.domain.model.mapper.map
import com.example.chotel.presentation.model.RoomBookingDetailsPresentationDTO
import com.example.chotel.presentation.utils.Rub
import org.koin.core.annotation.Factory

fun interface ToPresentationRoomBookingMapper :
    ToPresentationMapper<RoomBookingDetails, RoomBookingDetailsPresentationDTO>

fun interface ToPresentationRoomBookingPricesMapper : ToPresentationMapper<
        RoomBookingDetails.Prices, RoomBookingDetailsPresentationDTO.Prices>

@Factory
class ToPresentationRoomBookingPricesMapperImpl : ToPresentationRoomBookingPricesMapper {
    override fun RoomBookingDetails.Prices.map()
        = RoomBookingDetailsPresentationDTO.Prices(
            fuel = fuel.Rub,
            service = service.Rub,
            tour = tour.Rub,
            total = total.Rub,
        )
}

@Factory
class ToPresentationRoomBookingMapperImpl(
    private val hotelMapper: ToPresentationHotelMapper,
    private val pricesMapper: ToPresentationRoomBookingPricesMapper,
) : ToPresentationRoomBookingMapper {
    override fun RoomBookingDetails.map() = RoomBookingDetailsPresentationDTO(
        arrivalCountry = arrivalCountry,
        departureCountry = departureCountry,
        hotel = hotelMapper.map(hotel),
        nightsAmount = nightsAmount,
        nutrition = nutrition,
        room = room,
        tourDatesSpan = "$tourStartDate – $tourEndDate",
        prices = pricesMapper.map(prices)
    )
}