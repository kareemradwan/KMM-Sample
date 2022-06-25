package com.fursati.core

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module(override = true) {


    single<Settings> {
        AndroidSettings(get())
    }
    val baseKermit = Kermit(LogcatLogger()).withTag("KampKit")
    factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
}

