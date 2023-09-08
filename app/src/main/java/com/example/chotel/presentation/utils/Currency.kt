package com.example.chotel.presentation.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency

val Int.Rub get() = NumberFormat.getCurrencyInstance().apply {
    maximumFractionDigits = 0
    currency = Currency.getInstance("RUB")
}.format(this)