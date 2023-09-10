package com.example.chotel.data.repository

import com.example.chotel.data.datasource.remote.HotelRemoteDataSource
import com.example.chotel.data.model.remote.mapper.RemoteMappers
import com.example.chotel.domain.model.mapper.map
import com.example.chotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Singleton

@Singleton
class HotelRepositoryImpl(
    private val remoteDataSource: HotelRemoteDataSource,
    private val remoteMappers: RemoteMappers,
): HotelRepository {
    override fun getHotelInfo() = flow {
        val response = remoteDataSource.getHotelInfo()
            .map(remoteMappers.hotel::map)
            .getOrNull()!!
        emit(response)
    }

    override fun getHotelRooms()= flow {
        val response = remoteDataSource.getHotelRooms()
            .map(remoteMappers.rooms::map)
            .getOrNull()!!
        emit(response)
    }

    override fun getRoomBookingDetails()= flow {
        val response = remoteDataSource.getRoomBookingDetails()
            .map(remoteMappers.roomBooking::map)
            .getOrNull()!!
        emit(response)
    }
}