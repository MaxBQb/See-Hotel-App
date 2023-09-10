package com.example.chotel.data.datasource.remote

import com.example.chotel.data.model.remote.HotelRemoteDTO
import com.example.chotel.data.model.remote.HotelRoomBookingDetailsRemoteDTO
import com.example.chotel.data.model.remote.HotelRoomsRemoteDTO
import retrofit2.http.GET

interface CHotelAPI {
    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelInfo(): HotelRemoteDTO

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getHotelRooms(): HotelRoomsRemoteDTO

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getRoomBookingDetails(): HotelRoomBookingDetailsRemoteDTO
}