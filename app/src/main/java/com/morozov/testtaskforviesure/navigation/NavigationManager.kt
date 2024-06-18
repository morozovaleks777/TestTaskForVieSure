package com.morozov.testtaskforviesure.navigation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class NavigationManager @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val coroutineScope: CoroutineScope,

) {
    private val _event = MutableSharedFlow<NavigationAction>()

    val events = _event.asSharedFlow()

    fun send(event: NavigationAction) {
        coroutineScope.launch(dispatcher) {
         //   Timber.d(event.toString())
            _event.emit(event)
        }
    }
}