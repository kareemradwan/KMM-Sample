package com.kradwan.test_register_controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fursati.core.data.model.account.register.RegisterRequest
import com.fursati.core.data.model.account.register.User
import com.fursati.core.presentation.account.register.RegisterCallback
import com.fursati.core.presentation.account.register.RegisterController

class MainActivity : AppCompatActivity() {

    var viewModel: RegisterController = RegisterController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.callback = object : RegisterCallback {
            override fun goToHome() {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }

            override fun goToVerifyPhoneCode() {

            }

            override fun onLoading(status: Boolean) {
                findViewById<ProgressBar>(R.id.progress).visibility =
                    if (status) View.VISIBLE else View.GONE
            }

            override fun onRegisterError(message: String) {
                Toast.makeText(this@MainActivity, "Error: ${message}", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.register(
            RegisterRequest(
                "karefsdfem@gmaigfl.com",
                "Password1!",
                "Password1!",
                "job_seeker",
                "kareem_123"

            )
        )

    }
}