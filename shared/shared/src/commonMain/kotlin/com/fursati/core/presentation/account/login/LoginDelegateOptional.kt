package com.fursati.core.presentation.account.login

interface LoginDelegateOptional {
    fun onLoginSuccess() {}

    fun onLoginFailure() {}

    fun onLoadNews(news: List<String>)
}
