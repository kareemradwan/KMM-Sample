package com.fursati.core.presentation.account.register

import com.fursati.core.data.api.ApiService
import com.fursati.core.data.lang.Lang
import com.fursati.core.data.model.account.register.RegisterRequest
import com.fursati.core.presentation._utils.SessionManager
import com.fursati.core.presentation.coroutines.ViewModel
import io.ktor.client.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RegisterController : ViewModel(), KoinComponent {


    val strings: Lang by inject()
    val httpClient: HttpClient by inject()

    val sessionManager: SessionManager by inject()
    internal val apiService: ApiService by inject()

    var callback: RegisterCallback? = null

    fun register(request: RegisterRequest) {

        callback?.onLoading(true)

        // TODO Input Validations
        if (request.email.isEmpty() || request.email.length < 3) {
            callback?.onLoading(false)
            callback?.onRegisterError(strings.email_invalid)
            return
//            throw  IllegalArgumentException("Email is Invalid ")

        }

        if (request.password.isEmpty() || request.password.length < 6) {
            callback?.onLoading(false)
            callback?.onRegisterError(strings.password_is_invalid)
//            throw  IllegalArgumentException("Password is Invalid ")
            return
        }

        // TODO  API Call


        viewModelScope.launch {
            // TODO Handle Response
            val response = apiService.register(request)

            callback?.onLoading(false)
            if (response.status == true) {

                // TODO
                sessionManager.setUser(response.user)
                callback?.goToHome()
            } else {
                callback?.onRegisterError(response.message ?: "no-error")
                // TODO
            }
            // TODO Notifiy UI

        }


    }

}