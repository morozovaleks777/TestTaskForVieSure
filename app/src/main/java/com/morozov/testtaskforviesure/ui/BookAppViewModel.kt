package com.morozov.testtaskforviesure.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.navigation.NavigationAction
import com.morozov.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


sealed class WDAppEvent {
    data class Navigation(val action: NavigationAction) : WDAppEvent()
}

@HiltViewModel
class BookAppViewModel@Inject constructor(
    navigationManager: NavigationManager,

    ) : ViewModel() {

    private val _events = MutableSharedFlow<WDAppEvent>()
    val events = _events.asSharedFlow()

    init {
        navigationManager.events
            .onEach { _events.emit(WDAppEvent.Navigation(it)) }
            .launchIn(viewModelScope)
    }
}