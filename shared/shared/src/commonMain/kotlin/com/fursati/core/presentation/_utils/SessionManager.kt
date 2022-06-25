package com.fursati.core.presentation._utils

import co.touchlab.stately.ensureNeverFrozen
import com.fursati.core.data.model.account.register.User
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SessionManager : KoinComponent {

    init {
        ensureNeverFrozen()
    }

    private val settings: Settings by inject()

    fun setToken(token: String) {
        settings.putString("KEY_TOKEN", token)
    }

    fun getToken(): String {
        return settings["KEY_TOKEN"] ?: ""
    }

    fun getBearerToken(): String {
        val token = getToken()
        return if (token.startsWith("bearer")) token else "bearer ${token}"
    }

    fun getLang(default: String = "en"): String {
        return settings["app_lang"] ?: default
    }

    fun setLang(lang: String) {
        settings.putString("app_lang", lang)
    }


    fun setUser(user: User?) {

        if (user == null) {
            return
        }
        val userString = Json.encodeToString(User.serializer(), user)
        settings.putString("user_obj", userString)

    }

    fun getUser(): User? {
        try {
            return Json.decodeFromString(User.serializer(), settings.getString("user_obj"))
        } catch (ex: Exception) {
            return null
        }
    }


}
