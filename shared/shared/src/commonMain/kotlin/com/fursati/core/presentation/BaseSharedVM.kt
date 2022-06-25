package com.fursati.core.presentation

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.fursati.core.data.lang.Lang
import com.fursati.core.presentation._utils.DataState
import com.fursati.core.presentation._utils.SessionManager
import com.fursati.core.presentation.coroutines.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

open class BaseSharedVM<ViewState>(
    var onDataState: ((DataState<ViewState>) -> Unit)? = null
) : ViewModel(), KoinComponent {


    protected val log: Kermit by inject { parametersOf("BaseViewModel") }


    protected val sessionManager: SessionManager by inject()
    protected val strings: Lang by inject()

    private var loaders: Int = 0


    public val _stateFlow: MutableStateFlow<DataState<ViewState>> =
        MutableStateFlow(DataState.Loading(false))
    val stateFlow: StateFlow<DataState<ViewState>> = _stateFlow


    init {
        ensureNeverFrozen()
        viewModelScope.launch {
            log.v { "Exposing flow through callbacks" }
            _stateFlow.collect { dataState ->
                onDataState?.let {
                    it(dataState)
                }
            }
        }
    }


    fun startLoading() {
        if (!isLoading()) {
            _stateFlow.value = DataState.Loading(true)
        }
        loaders += 1
    }

    fun stopLoading(): Boolean? {
        loaders -= 1
        return if (loaders > 0) null else false
    }


    public fun isLoading() = loaders != 0


}

