package com.fursati.core

import co.touchlab.kermit.Kermit
import com.fursati.core.data.api.ApiService
import com.fursati.core.data.lang.Lang
import com.fursati.core.data.lang.ar.ArLang
import com.fursati.core.data.lang.en.EnLang
import com.fursati.core.presentation._utils.SessionManager
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.datetime.Clock
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module


fun getLang(sessionManager: SessionManager): Lang {
    val lang = sessionManager.getLang("en")
    return if ("en" == lang)
        EnLang()
    else
        ArLang()
}

fun initKoin(appModule: Module, httpCl: HttpClient? = null): KoinApplication {


    val httpClint = client( Kermit())

    val module = module {

        factory<Lang> {
            getLang(sessionManager)
        }

        single<SessionManager> {
            sessionManager
        }

        single<HttpClient> {
            httpClint
        }

        single<ApiService> {
            ApiService()
        }

    }
    val koinApplication = startKoin {
        modules(
            appModule,
            module,
            coreModule,
            platformModule,
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
    val koin = koinApplication.koin
    val doOnStartup =
        koin.get<() -> Unit>() // doOnStartup is a lambda which is implemented in Swift on iOS side
    doOnStartup.invoke()

    val kermit = koin.get<Kermit> { parametersOf(null) }
    return koinApplication
}


public fun client(
    log: Kermit,
    engine: HttpClientEngine = HttpClient().engine
): HttpClient {

    return HttpClient(engine) {
        followRedirects = false
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
                useAlternativeNames = false
                encodeDefaults = false
            })

            useDefaultTransformers = false
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    log.v("Network") { message }
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        defaultRequest {
            header("lang", sessionManager.getLang("en"))
            header("Content-Type", "application/json")
            header("Accept", "application/json")

            val token = sessionManager.getBearerToken()
            if (token.isNotEmpty()) {
                header("Authorization", token)
            }
        }
    }

}


var sessionManager = SessionManager()

private val coreModule = module {
    single<Clock> {
        Clock.System
    }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

expect val platformModule: Module
