package com.example.chotel.presentation.screen.rooms

import com.example.chotel.domain.model.mapper.map
import com.example.chotel.presentation.model.mapper.PresentationMappers
import com.example.chotel.presentation.screen.core.StatefulViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel
import com.example.chotel.presentation.screen.rooms.RoomsContract as Ui

@KoinViewModel
class RoomsViewModel(
    hotelRepository: com.example.chotel.domain.repository.HotelRepository,
    mappers: PresentationMappers,
) : StatefulViewModel<Ui.State>() {
    override fun getInitialState() = Ui.State()

    private val _rooms = hotelRepository.getHotelRooms().map {
        it.map(mappers.room::map)
    }

    override val uiState = combine(_uiState, _rooms) { state, rooms ->
        state.copy(rooms = rooms)
    }.stateIn()
}