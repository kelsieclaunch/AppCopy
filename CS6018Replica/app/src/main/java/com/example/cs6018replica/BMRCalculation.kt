package com.example.cs6018replica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BMRcalculation : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    //private lateinit var showBMR : TextView
    private lateinit var showCalories : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bmr_calculation)
        appDb = AppDatabase.getDatabase(this)
        //showBMR = findViewById(R.id.bmrOutputText)
        showCalories = findViewById(R.id.calorieOutputText)

        //showBMR.text = calculateBMR().toString()
        showCalories.text = calculateCalories().toString()

        appDb.userDAO().updateBMR(calculateCalories())

    }

    fun onBackButton(view : View) {
        val intent = Intent(this, ProfileHomePage::class.java ). apply{

        }
        startActivity(intent)

    }

    fun calculateBMR(): Int {
        val weightlbs = appDb.userDAO().getWeight()
        val weightkg = weightlbs / 2.205
        val heightInches = appDb.userDAO().getHeight().toInt()
        val heightCm = heightInches * 2.54
        val age = appDb.userDAO().getAge()

        val menBMR = 66.47 + (13.75 * weightkg) + (5.003 * heightCm) - (6.755 * age)

        val womenBMR = 655.1 + (9.563 * weightkg) + (1.850 * heightCm) - (4.676 * age)

        if(appDb.userDAO().getSex() == "Female"){
            return womenBMR.toInt()
        }
        if(appDb.userDAO().getSex() == "Male"){
            return menBMR.toInt()
        }
        else return 1

    }

    fun calculateCalories(): Int {
        val sedenatry = calculateBMR() * 1.2
        val mild = calculateBMR() * 1.3
        val moderate = calculateBMR() * 1.5
        val heavy = calculateBMR() * 1.7
        val extreme = calculateBMR() * 1.9
        if(appDb.userDAO().getLevel() == "Sedentary") {
            return sedenatry.toInt()
        }
        if(appDb.userDAO().getLevel() == "Lightly Active") {
            return mild.toInt()
        }
        if(appDb.userDAO().getLevel() == "Moderately Active") {
            return moderate.toInt()
        }
        if(appDb.userDAO().getLevel() == "Very Active") {
            return heavy.toInt()
        }
        if(appDb.userDAO().getLevel() == "Heavily Active") {
            return extreme.toInt()
        }
        else return 1
    }
    //BMR calculation for men (in metric) : 66.47 + (13.75 * weight in kg) + (5.003 * height in cm) - (6.755 * age in years)
    //BMR calculation for women(metric) : 655.1 + ( 9.563 x weight in kg ) + ( 1.850 x height in cm ) - ( 4.676 x age in years )

    //Daily calories needed based on activity level:
    //Sedentary : BMR * 1.2
    //Mild : BMR * 1.3 - 1.375 (range)
    //Moderate : BMR * 1.5 - 1.55 (range)
    //Heavy : BMR * 1.7
    //Extreme : BMR * 1.9

    //converters:
    //kg to lbs: 1:2.205 (1 kg = 2.205 lbs; multiply mass value by 2.205)
    //cm to inches: 1:0.394 (1 cm = 0.394 in; divide length value by 2.54)

}