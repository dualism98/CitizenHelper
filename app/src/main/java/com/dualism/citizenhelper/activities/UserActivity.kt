package com.dualism.citizenhelper.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dualism.citizenhelper.R
import com.dualism.citizenhelper.fragments.main.ProfileFragment
import com.dualism.citizenhelper.fragments.main.RequestsFragment
import com.dualism.citizenhelper.fragments.main.TopicsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        supportActionBar?.hide();

        setContentView(R.layout.activity_user)

        loadFragment(TopicsFragment())


        //getting bottom navigation view and attaching the listener
        val navigation = findViewById<BottomNavigationView>(R.id.bottomNav)
        navigation.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.navigation_themes -> {
                    loadFragment(TopicsFragment())
                }
                R.id.navigation_requests -> {
                    loadFragment(RequestsFragment())
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }
}