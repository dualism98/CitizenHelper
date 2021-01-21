package com.dualism.dotaheroes.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dualism.dotaheroes.R

class SplashActivity: AppCompatActivity() {
    private val SPLASH_TIME_OUT = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        getSupportActionBar()?.hide();

        setContentView(R.layout.activity_splash)

        val userData = getSharedPreferences(
            "user_storage",
            Context.MODE_PRIVATE
        )

        val token = userData.getString("token", "NONE")
        println("TOKEN: " + token)
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
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, SPLASH_TIME_OUT)
        }
    }
}