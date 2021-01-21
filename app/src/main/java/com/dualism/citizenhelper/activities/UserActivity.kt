package com.dualism.citizenhelper.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dualism.citizenhelper.R
import kotlinx.android.synthetic.main.activity_user.*


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        supportActionBar?.hide();

        setContentView(R.layout.activity_user)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_themes -> {
                    setContent("Home")
                    true
                }
                R.id.navigation_requests -> {
                    setContent("Notification")
                    true
                }
                R.id.navigation_profile -> {
                    setContent("Search")
                    true
                }
                else -> false
            }
        }
    }

    private fun setContent(content: String) {
        setTitle(content)
        tvLabel.text = content
    }
}