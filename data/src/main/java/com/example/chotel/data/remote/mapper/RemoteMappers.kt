package com.example.chotel.data.remote.mapper

import org.koin.core.annotation.Factory

@Factory
class RemoteMappers(
    val hotel: FromRemoteHotelMapper,
    val rooms: FromRemoteRoomsMapper,
    val roomBooking: FromRemoteRoomBookingMapper,
)
