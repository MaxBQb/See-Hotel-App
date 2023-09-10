package com.example.chotel.presentation.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency
import java.util.Locale

val Int.Rub get() = NumberFormat.getCurrencyInstance(Locale("ru-ru")).apply {
    maximumFractionDigits = 0
    currency = Currency.getInstance("RUB")
}.format(this)