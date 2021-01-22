package com.dualism.citizenhelper.activities

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.dualism.citizenhelper.R


class AuthActivity: MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        supportActionBar?.hide();
        setContentView(R.layout.activity_main)
    }
}