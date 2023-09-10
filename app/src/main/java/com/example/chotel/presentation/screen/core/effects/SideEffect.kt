package com.example.chotel.presentation.screen.core.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import kotlinx.coroutines.CoroutineScope


@Composable
@NonRestartableComposable
inline fun <reified T: UiSideEffect> SideEffect(
    effects: UiSideEffectsHolder,
    noinline onConsumed: (EffectKey) -> Unit,
    immediate: Boolean = false,
    vararg keys: Any?,
    crossinline action: suspend CoroutineScope.(T) -> Unit,
) = LaunchedEffect(effects, onConsumed, *keys) {
    val effect = effects.get<T>() ?: return@LaunchedEffect
    if (immediate)
        onConsumed(EffectKey<T>())
    action(effect)
    if (!immediate)
        onConsumed(EffectKey<T>())
}
