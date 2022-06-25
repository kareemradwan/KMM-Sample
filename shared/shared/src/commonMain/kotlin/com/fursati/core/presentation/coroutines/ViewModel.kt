package com.fursati.core.presentation.coroutines


import kotlinx.coroutines.CoroutineScope

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect open class ViewModel() {
    protected val viewModelScope: CoroutineScope

    open fun onCleared()
}


