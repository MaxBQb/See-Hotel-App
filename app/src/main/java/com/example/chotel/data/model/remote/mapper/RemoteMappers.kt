package com.example.chotel.data.model.remote.mapper

import org.koin.core.annotation.Factory

@Factory
class RemoteMappers(
    val hotel: FromRemoteHotelMapper,
    val rooms: FromRemoteRoomsMapper,
    val roomBooking: FromRemoteRoomBookingMapper,
)
