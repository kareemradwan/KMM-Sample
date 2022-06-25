package com.fursati.core.presentation.account.register

import com.fursati.core.data.model.account.register.User

interface RegisterCallback {

//    fun onRegisterSuccessfully(user: User)

    fun onRegisterError(message: String)
    fun goToHome()
    fun goToVerifyPhoneCode()
    fun onLoading(status: Boolean)


    // TODO
}