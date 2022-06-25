package com.fursati.core.presentation.splash

import com.fursati.core.presentation._utils.SessionManager
import com.fursati.core.presentation.coroutines.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface SplashCallback {

    fun goToHome()
    fun goToLogin()
    fun goToVerification()
    fun blockUser()


}


class SplashController : ViewModel(), KoinComponent {

    val sessionManager: SessionManager by inject()

    var callback: SplashCallback? = null

    init {


    }

    public fun checkUserStatus(){

        if (sessionManager.getUser() == null){
            // TODO No user _ go To Login
            callback?.goToLogin()
        }else{
            callback?.goToHome()
            // TODO Go To Home
        }
    }
}