package com.dualism.citizenhelper.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dualism.citizenhelper.R

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_user)
    }
}