package com.fursati.core.presentation.account.login

import com.fursati.core.presentation.coroutines.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    fun loadNews() {
        viewModelScope.launch {
            delay(3000)
            delegate?.onLoadNews(
                arrayListOf(
                    "News 1",
                    "News 2",
                    "News 3",
                )
            )
        }
    }

    // TODO Login Deleage

    var delegate: LoginDelegateOptional? = null

    private suspend fun login(username: String, password: String): String {
        delay(3000)
        return "hello "
    }

    fun loginUserNameAndPassword(username: String, password: String) {
        // TODO Make The Validation Here
        viewModelScope.launch {
            var response = login(username, password)
            // TOFO
            if (response.length > 2) {
                delegate?.onLoginSuccess()
                return@launch
            }

            delegate?.onLoginFailure()
        }
    }


}