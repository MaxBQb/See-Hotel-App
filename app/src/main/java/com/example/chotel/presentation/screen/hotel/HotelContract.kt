package com.example.chotel.presentation.screen.hotel

import androidx.compose.runtime.Immutable
import com.example.chotel.presentation.model.HotelDetailsPresentationDTO
import com.example.chotel.presentation.screen.core.UiState

interface HotelContract {
    @Immutable
    data class State(
        val hotelDetails: HotelDetailsPresentationDTO? = null,
    ): UiState
}