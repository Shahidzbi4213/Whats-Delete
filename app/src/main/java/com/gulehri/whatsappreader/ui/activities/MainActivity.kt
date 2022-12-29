package com.gulehri.whatsappreader.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gulehri.whatsappreader.R
import com.gulehri.whatsappreader.databinding.ActivityMainBinding
import com.gulehri.whatsappreader.utils.Extensions.isNotificationServiceEnable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SplashScreen Installation Call
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)

        setupBottomSheet()
        checkAvailability()

    }

    private fun checkAvailability() {

        isNotificationServiceEnable {
            if (!it) startActivity(
                Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
            )
        }
    }

    private fun setupBottomSheet() {
        navController =
            (supportFragmentManager.findFragmentById(R.id.host) as NavHostFragment).navController
        binding.bottomBar.setupWithNavController(navController)


    }


}