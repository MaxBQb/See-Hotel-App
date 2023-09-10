package com.example.chotel.presentation.screen.booking.order_paid

import androidx.compose.runtime.Immutable
import com.example.chotel.presentation.screen.core.UiState
import kotlin.random.Random

interface OrderPaidContract {
    @Immutable
    data class State(
        val orderId: Int = Random.nextInt(100000, 999999),
    ): UiState
}