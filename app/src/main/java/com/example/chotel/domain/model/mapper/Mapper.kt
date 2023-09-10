package com.example.chotel.domain.model.mapper

fun interface Mapper<in T, out R> {
    fun T.map(): R
}

fun <T, R> Mapper<T, R>.map(model: T) = model.map()
