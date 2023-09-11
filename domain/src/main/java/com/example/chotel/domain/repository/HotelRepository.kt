package com.example.chotel.domain.repository

import com.example.chotel.domain.model.HotelDetails
import com.example.chotel.domain.model.Room
import com.example.chotel.domain.model.RoomBookingDetails
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    fun getHotelInfo(): Flow<HotelDetails>
    fun getHotelRooms(): Flow<List<Room>>
    fun getRoomBookingDetails(): Flow<RoomBookingDetails>
}