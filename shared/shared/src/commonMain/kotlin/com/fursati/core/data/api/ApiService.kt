package com.fursati.core.data.api

import co.touchlab.stately.ensureNeverFrozen
import com.fursati.core.data.model.account.register.RegisterRequest
import com.fursati.core.data.model.account.register.RegisterResponse
import io.ktor.client.request.*
import io.ktor.http.*

internal class ApiService : BaseApiService() {


    init {
        ensureNeverFrozen()
    }

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val response =
            httpClient.post<RegisterResponse>("http://178.62.198.37/api/v1/register") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                body = request
            }

        return response
    }

    // http://www.facebook.com/login.php


}

