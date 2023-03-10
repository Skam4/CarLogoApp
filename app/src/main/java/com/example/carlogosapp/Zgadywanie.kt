package com.example.carlogosapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.tv.TvContract.Channels.Logo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock.sleep
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.carlogosapp.R
import java.util.Collections.shuffle

class Zgadywanie : AppCompatActivity() {

    // oryginalne tablice z nazwami marek i logami
    private val carBrands: Array<String> = arrayOf("Audi", "BMW", "Mercedes-Benz", "Ford", "Toyota", "Honda", "Chevrolet", "Volvo", "Alfa Romeo", "Acura",
    "Bentley", "Bugatti", "Buick", "Cadillac", "Chrysler", "Corvette", "Citroën", "Dodge", "Dacia", "Ferrari", "Ford Mustang", "Fiat", "Hyundai",
    "Jeep", "Jaguar", "Kia", "Lamborghini", "Lexus", "Maserati", "Mazda", "Mitsubishi", "Nissan", "Opel", "Porsche", "Peugeot", "Rolls-Royce", "Renault",
    "Subaru", "Suzuki", "Škoda", "Seat", "Tesla", "Volkswagen")

    private val carBrandLogos: Array<Int> = arrayOf(
        R.drawable.audi_logo, R.drawable.bmw_logo, R.drawable.mercedes_logo,
        R.drawable.ford_logo, R.drawable.toyota_logo, R.drawable.honda_logo,
        R.drawable.chevrolet_logo, R.drawable.volvo_logo, R.drawable.alfaromeo_logo,
        R.drawable.acura_logo, R.drawable.bentley_logo, R.drawable.bugatti_logo,
        R.drawable.buick_logo, R.drawable.cadillac_logo,
        R.drawable.chrysler_logo, R.drawable.corvette_logo, R.drawable.citroen_logo,
        R.drawable.dodge_logo, R.drawable.dacia_logo, R.drawable.ferrari_logo,
        R.drawable.fordmustang_logo, R.drawable.fiat_logo, R.drawable.hyundai_logo,
        R.drawable.jeep_logo, R.drawable.jaguar_logo, R.drawable.kia_logo,
        R.drawable.lamborghini_logo, R.drawable.lexus_logo, R.drawable.maserati_logo,
        R.drawable.mazda_logo, R.drawable.mitsubishi_logo, R.drawable.nissan_logo,
        R.drawable.opel_logo, R.drawable.porsche_logo, R.drawable.peugeot_logo,
        R.drawable.rollsroyce_logo, R.drawable.renault_logo, R.drawable.subaru_logo,
        R.drawable.suzuki_logo, R.drawable.skoda_logo, R.drawable.seat_logo,
        R.drawable.tesla_logo, R.drawable.volkswagen_logo
    )

    private var Marki = carBrands.copyOf()
    private var Loga = carBrandLogos.copyOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zgadywanie)

        val tło = findViewById<ConstraintLayout>(R.id.tloooo)

        val wróć = findViewById<Button>(R.id.GoBack)

        wróć.setOnClickListener{
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }

        // pobranie referencji do przycisków z widoku
        val button1 = findViewById<Button>(R.id.Samochod1)
        val button2 = findViewById<Button>(R.id.Samochod2)
        val button3 = findViewById<Button>(R.id.Samochod3)
        val button4 = findViewById<Button>(R.id.Samochod4)

        val sharedPref = getSharedPreferences("DARK_MODE_PREF", MODE_PRIVATE)
        val isDarkModeEnabled = sharedPref.getBoolean("DARK_MODE", false)

        if(isDarkModeEnabled)
        {
            tło.setBackgroundColor(Color.DKGRAY)
        }
        else
        {
            tło.setBackgroundColor(Color.WHITE)
        }


        // wybranie ImageView z widoku
        val logoImageView = findViewById<ImageView>(R.id.LOGO)

        // losowanie indeksu marki samochodowej
        val randomIndex = (0 until Marki.size).random()

        // losowanie indeksu przycisku, który zawiera poprawną odpowiedź
        val correctAnswerIndex = (0..3).random()

        // pobranie nazwy marki i odpowiadającego jej logo
        val wybranaMarka = Marki[randomIndex]
        val wybraneLogo = Loga[randomIndex]

        // przypisanie nazwy poprawnej marki samochodowej do zmiennej
        val correctAnswer = wybranaMarka

        // usuwanie odgadniętego loga z tablicy
        Loga = Loga.filterIndexed { index, _ -> index != wybraneLogo }.toTypedArray()

        // utworzenie listy zawierającej wszystkie nazwy marek samochodowych oprócz poprawnej
        val otherCarBrands = carBrands.filter { it != correctAnswer }

        // przetasowanie listy
        shuffle(otherCarBrands)

        //wybieranie zdjęcia
        logoImageView.setImageResource(wybraneLogo)

        // ustawienie tekstu na przyciskach
        button1.text = if (correctAnswerIndex == 0) correctAnswer else otherCarBrands[0]
        button2.text = if (correctAnswerIndex == 1) correctAnswer else otherCarBrands[1]
        button3.text = if (correctAnswerIndex == 2) correctAnswer else otherCarBrands[2]
        button4.text = if (correctAnswerIndex == 3) correctAnswer else otherCarBrands[3]

        // dodanie listenerów do przycisków
        button1.setOnClickListener {
            checkAnswer(button1, correctAnswer)
        }

        button2.setOnClickListener {
            checkAnswer(button2, correctAnswer)
        }

        button3.setOnClickListener {
            checkAnswer(button3, correctAnswer)
        }

        button4.setOnClickListener {
            checkAnswer(button4, correctAnswer)
        }

    }

    override fun onResume() {
        super.onResume()

        // przywrócenie oryginalnych tablic po wznowieniu aktywności
        Marki = carBrands.copyOf()
        Loga = carBrandLogos.copyOf()
    }


    fun checkAnswer(button: Button, correctAnswer: String) {
        if (button.text == correctAnswer) {
            button.setBackgroundColor(Color.parseColor("#00FF00")) // ustawienie koloru tła na zielony
            Handler().postDelayed({
                // ustawienie koloru tła na domyślny po 500 milisekundach
                button.setBackgroundColor(Color.parseColor("#FFFFFF"))
                // przejście do kolejnego zgadywania
                recreate()
            }, 500)
        }
    }


}
