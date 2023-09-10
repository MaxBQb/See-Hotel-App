package com.example.chotel.presentation.screen.rooms

import androidx.compose.runtime.Immutable
import com.example.chotel.presentation.model.RoomPresentationDTO
import com.example.chotel.presentation.screen.core.UiState

interface RoomsContract {
    @Immutable
    data class State(
        val rooms: List<RoomPresentationDTO> = emptyList()
    ): UiState
}