package com.example.chotel.presentation.screen.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chotel.presentation.screen.core.effects.UiEffectAwareState
import com.example.chotel.presentation.screen.core.effects.UiSideEffect
import com.example.chotel.presentation.screen.core.effects.UiSideEffectConsumed
import com.example.chotel.presentation.screen.core.effects.withEffect
import com.example.chotel.presentation.screen.core.effects.withoutEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class StatefulViewModel<S : UiState> : ViewModel() {
    private val _initialState: S by lazy { getInitialState() }
    protected val _uiState = MutableStateFlow(_initialState)
    open val uiState = _uiState.asStateFlow()
    protected abstract fun getInitialState(): S
    protected inline fun setState(function: (S) -> S) = _uiState.update(function)
    fun Flow<S>.stateIn() = stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _initialState
    )
}

abstract class PureInteractiveViewModel<S : UiState, E : UiEvent> : StatefulViewModel<S>() {
    private val _uiEvent = MutableSharedFlow<E>()
    fun onEvent(event: E) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected abstract fun handleEvent(event: E)
    protected fun startEventsHandling() = viewModelScope.launch {
        _uiEvent.collect {
            handleEvent(it)
        }
    }

    init {
        startEventsHandling()
    }
}

abstract class BaseViewModel<S : UiEffectAwareState, E : UiEvent, SE: UiSideEffect> :
    PureInteractiveViewModel<S, E>() {

    protected fun handleEffectConsumption(
        event: UiSideEffectConsumed
    ) = setState { it.withoutEffect(event.effect) }

    protected inline fun <reified E: SE> setEffect(effect: () -> E)
        = setState { it.withEffect(effect()) }
}
