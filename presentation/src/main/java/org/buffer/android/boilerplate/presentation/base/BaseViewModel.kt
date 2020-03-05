package org.buffer.android.boilerplate.presentation.base

import kotlinx.coroutines.flow.Flow

interface BaseViewModel<I : BaseIntent, S : BaseViewState> {
    fun processIntents(intents: Flow<I>)

    fun states(): Flow<S>
}