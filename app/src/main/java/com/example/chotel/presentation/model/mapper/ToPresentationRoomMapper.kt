package com.example.chotel.presentation.model.mapper

import com.example.chotel.presentation.model.RoomPresentationDTO
import com.example.chotel.presentation.utils.Rub
import org.koin.core.annotation.Factory

fun interface ToPresentationRoomMapper : ToPresentationMapper<com.example.chotel.domain.model.Room, RoomPresentationDTO>

@Factory
class ToPresentationRoomMapperImpl : ToPresentationRoomMapper {
    override fun com.example.chotel.domain.model.Room.map() = RoomPresentationDTO(
        images = images,
        name = name,
        features = features,
        price = price.Rub,
        priceNote = priceNote
    )
}
