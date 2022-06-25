package com.fursati.core.presentation.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(DispatcherProvider.mainDispatcher() + SupervisorJob())
}