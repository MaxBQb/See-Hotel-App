package com.example.chotel.presentation.screen.core.effects

import kotlin.reflect.KClass

@JvmInline
value class EffectKey private constructor(val value: String) {
    constructor(value: KClass<*>) : this(value.java.toString())
}

inline fun <reified T> EffectKey(): EffectKey = EffectKey(T::class)