package com.example.chotel.presentation.model.mapper

import org.koin.core.annotation.Factory

@Factory
class PresentationMappers(
    val hotel: ToPresentationHotelDetailsMapper,
    val room: ToPresentationRoomMapper,
    val roomBooking: ToPresentationRoomBookingMapper,
)
