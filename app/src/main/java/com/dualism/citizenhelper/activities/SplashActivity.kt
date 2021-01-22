package com.dualism.citizenhelper.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.dualism.citizenhelper.R
import com.dualism.citizenhelper.services.StorageService

class SplashActivity: AppCompatActivity() {
    private val SPLASH_TIME_OUT = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        supportActionBar?.hide();

        setContentView(R.layout.activity_splash)

        val storage = StorageService()
        val token = storage.getString(this,"token", "NONE")

        println("TOKEN: $token")

        if(token != "NONE"){
            println(token)
            Handler().postDelayed(
                {
                    val intent = Intent(this@SplashActivity, UserActivity::class.java)
                    startActivity(intent)
                    finish()
                }, SPLASH_TIME_OUT)
        }
        else{
            Handler().postDelayed(
                {
                    val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }, SPLASH_TIME_OUT)
        }
    }
}