package com.example.chotel.data.model.remote.mapper

import com.example.chotel.data.model.remote.HotelRoomsRemoteDTO
import com.example.chotel.domain.model.Room
import com.example.chotel.domain.model.mapper.Mapper
import com.example.chotel.domain.model.mapper.map
import org.koin.core.annotation.Factory

fun interface FromRemoteRoomMapper : FromRemoteMapper<HotelRoomsRemoteDTO.Room, Room>
fun interface FromRemoteRoomsMapper : Mapper<HotelRoomsRemoteDTO, List<Room>>

@Factory
class FromRemoteRoomMapperImpl : FromRemoteRoomMapper {
    override fun HotelRoomsRemoteDTO.Room.map() = Room(
        id = id,
        images = images,
        name = name,
        features = features,
        price = price,
        priceNote = priceNote
    )
}

@Factory
class FromRemoteRoomsMapperImpl(
    private val mapper: FromRemoteRoomMapper
) : FromRemoteRoomsMapper {
    override fun HotelRoomsRemoteDTO.map(): List<Room>
        = rooms.map { mapper.map(it) }
}