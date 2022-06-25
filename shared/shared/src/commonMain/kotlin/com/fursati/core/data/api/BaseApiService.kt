package com.fursati.core.data.api

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.fursati.core.data.model.account.register.RegisterRequest
import com.fursati.core.data.model.account.register.RegisterResponse
import io.ktor.client.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal abstract class BaseApiService : KoinComponent {

    protected val httpClient: HttpClient by inject()

    protected val log: Kermit by inject { parametersOf("BaseViewModel") }
    protected val BASE_URL: String = "http://178.62.198.37/api/v1"

    init {
        ensureNeverFrozen()
    }

    abstract suspend fun register(request: RegisterRequest): RegisterResponse


}