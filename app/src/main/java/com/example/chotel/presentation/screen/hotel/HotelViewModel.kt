package com.example.chotel.presentation.screen.hotel

import com.example.chotel.domain.model.mapper.map
import com.example.chotel.presentation.model.mapper.PresentationMappers
import com.example.chotel.presentation.screen.core.StatefulViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel
import com.example.chotel.presentation.screen.hotel.HotelContract as Ui

@KoinViewModel
class HotelViewModel(
    hotelRepository: com.example.chotel.domain.repository.HotelRepository,
    mappers: PresentationMappers,
) : StatefulViewModel<Ui.State>() {
    override fun getInitialState() = Ui.State()

    private val _hotelDetails = hotelRepository.getHotelInfo().map(mappers.hotel::map)

    override val uiState = combine(_uiState, _hotelDetails) {
        state, hotelDetails -> state.copy(hotelDetails = hotelDetails)
    }.stateIn()
}