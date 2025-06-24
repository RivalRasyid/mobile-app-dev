package com.example.facedx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.facedx.databinding.ActivityMainBinding
import com.example.facedx.ui.auth.onboarding.OnboardingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val isLoggedIn = checkUserLoginStatus()

        if (!isLoggedIn) {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconSize = 70

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_chatbot, R.id.navigation_camera, R.id.navigation_settings)
        )


        navView.setupWithNavController(navController)

        val target = intent.getStringExtra("navigate_to")
        if (target == "camera") {
            navView.selectedItemId = R.id.navigation_camera
        }

    }

    private fun checkUserLoginStatus(): Boolean {
        val sharedPref = getSharedPreferences("UserPref", MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }
}
