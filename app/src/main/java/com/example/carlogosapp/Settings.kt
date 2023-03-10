package com.example.carlogosapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.View
import android.widget.ImageButton
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.carlogosapp.GlobalVariables
import com.example.carlogosapp.R

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPref = getSharedPreferences("DARK_MODE_PREF", MODE_PRIVATE)
        val DarkMode = findViewById<Switch>(R.id.DarkMode)
        val Cofnij = findViewById<ImageButton>(R.id.Cofnij)
        val tło = findViewById<ConstraintLayout>(R.id.tło)

        Cofnij.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

        val isDarkModeEnabled = sharedPref.getBoolean("DARK_MODE", false)
        DarkMode.isChecked = isDarkModeEnabled

        if (isDarkModeEnabled) {
            DarkMode.setBackgroundResource(R.drawable.rounded_background_dark);
            tło.setBackgroundColor(Color.GRAY)
            DarkMode.setTextColor(Color.WHITE)
        } else {
            DarkMode.setBackgroundResource(R.drawable.rounded_background_light);
            tło.setBackgroundColor(Color.WHITE)
            DarkMode.setTextColor(Color.BLACK)
        }

        DarkMode.setOnCheckedChangeListener { _, onSwitch ->

            val editor = sharedPref.edit()
            editor.putBoolean("DARK_MODE", onSwitch)
            editor.apply()

            if (onSwitch)
            {
                DarkMode.setBackgroundResource(R.drawable.rounded_background_dark);
                tło.setBackgroundColor(Color.GRAY)
                DarkMode.setTextColor(Color.WHITE)
            }
            else
            {
                DarkMode.setBackgroundResource(R.drawable.rounded_background_light);
                tło.setBackgroundColor(Color.WHITE)
                DarkMode.setTextColor(Color.BLACK)
            }

        }
    }



}
