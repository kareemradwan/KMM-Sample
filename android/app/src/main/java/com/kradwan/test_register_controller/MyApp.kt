package com.kradwan.test_register_controller

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.fursati.core.initKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            module {

                single<Context> {
                    this@MyApp
                }

                single<SharedPreferences> {
                    get<Context>()
                        .getSharedPreferences("app_shared", MODE_PRIVATE)
                }


                single {
                    {
                        Log.d("DDDD", "Hello from Android Application")
                    }
                }

            }
        )
    }
}