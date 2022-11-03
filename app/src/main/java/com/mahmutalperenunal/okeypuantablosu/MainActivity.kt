package com.mahmutalperenunal.okeypuantablosu

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.mahmutalperenunal.okeypuantablosu.databinding.ActivityMainBinding
import com.mahmutalperenunal.okeypuantablosu.anamenu.AnaMenu
import java.util.*
import kotlin.concurrent.timerTask

//app splash screen with timer
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferencesTheme: SharedPreferences

    private var theme: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesTheme = getSharedPreferences("appTheme", MODE_PRIVATE)

        checkTheme()

        timer()
    }


    private fun timer() {
        intent = Intent(applicationContext, AnaMenu::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        val startActivityTimer = timerTask {
            startActivity(intent)
        }

        val timer = Timer()
        timer.schedule(startActivityTimer, 1000)

    }


    /**
     * check last theme
     */
    private fun checkTheme() {
        theme = sharedPreferencesTheme.getInt("theme", 0)

        val appTheme = when (theme) {
            -1 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM //-1
            2 -> AppCompatDelegate.MODE_NIGHT_YES //2
            else -> AppCompatDelegate.MODE_NIGHT_NO //1
        }
        Log.d("MainActivity", "theme:$appTheme")
        AppCompatDelegate.setDefaultNightMode(appTheme)
    }

}