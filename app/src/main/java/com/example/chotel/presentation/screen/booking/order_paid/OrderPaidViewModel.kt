package com.example.chotel.presentation.screen.booking.order_paid

import com.example.chotel.presentation.screen.core.StatefulViewModel
import org.koin.android.annotation.KoinViewModel
import com.example.chotel.presentation.screen.booking.order_paid.OrderPaidContract as Ui

@KoinViewModel
class OrderPaidViewModel : StatefulViewModel<Ui.State>() {
    override fun getInitialState() = Ui.State()
}