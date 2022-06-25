package com.kradwan.test_register_controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fursati.core.presentation.splash.SplashCallback
import com.fursati.core.presentation.splash.SplashController

class SplashScreen : AppCompatActivity(), SplashCallback {


    lateinit var splashController: SplashController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashController = SplashController()
        splashController.callback = this

        splashController.checkUserStatus()


    }

    override fun blockUser() {

    }

    override fun goToHome() {
        Log.d("DDDD" , "Go To Home ")

        Intent(this, HomeActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun goToLogin() {
        Log.d("DDDD" , "Go To Login ")
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun goToVerification() {

    }
}