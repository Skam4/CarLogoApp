package com.example.carlogosapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.ContextCompat
import com.example.carlogosapp.GlobalVariables
import com.example.carlogosapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val PrzyciskUstawienia =  findViewById<Button>(R.id.options)
        val PrzyciskLearn = findViewById<Button>(R.id.Learn)

        PrzyciskUstawienia.setOnClickListener{
            val Intent = Intent(this,Settings::class.java)
            startActivity(Intent)
        }

        PrzyciskLearn.setOnClickListener{
            val Intent = Intent(this,Zgadywanie::class.java)
            startActivity(Intent)
        }

        val sharedPref = getSharedPreferences("DARK_MODE_PREF", MODE_PRIVATE)
        val isDarkModeEnabled = sharedPref.getBoolean("DARK_MODE", false)

        if(isDarkModeEnabled)
        {
            PrzyciskUstawienia.setTextColor(Color.LTGRAY)
            PrzyciskLearn.setTextColor(Color.LTGRAY)
            PrzyciskUstawienia.setBackgroundColor(Color.BLACK)
            PrzyciskLearn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#390642"))
            PrzyciskUstawienia.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#141680"))
        }
        else
        {
            PrzyciskUstawienia.setTextColor(Color.WHITE)
            PrzyciskLearn.setTextColor(Color.WHITE)
            PrzyciskLearn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#10b7c6"))
            PrzyciskUstawienia.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#73D7C0"))
        }

    }

}