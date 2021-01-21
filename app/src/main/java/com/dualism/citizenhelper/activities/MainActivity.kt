package com.dualism.dotaheroes.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.arellomobile.mvp.MvpAppCompatActivity
import com.dualism.dotaheroes.R


class MainActivity: MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)
    }
}