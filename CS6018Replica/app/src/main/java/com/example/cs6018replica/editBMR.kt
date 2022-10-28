package com.example.cs6018replica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner

class EditBMR : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var spinnerInches : Spinner
    private lateinit var spinnerPounds : Spinner
    private lateinit var spinnerLevel : Spinner

    private lateinit var heightButton : Button
    private lateinit var weightButton : Button
    private lateinit var levelButton : Button
    private lateinit var backButton : Button
    private lateinit var bmrRecalc : Button

    private lateinit var heightString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bmr)
        appDb = AppDatabase.getDatabase(this)
        spinnerInches = findViewById(R.id.updateHeight)
        spinnerPounds = findViewById(R.id.updateWeight)
        spinnerLevel = findViewById(R.id.updateActivity)

        heightButton = findViewById(R.id.updateHeightButton)
        weightButton = findViewById(R.id.updateWeightButton)
        levelButton = findViewById(R.id.updateLevelButton)
        backButton = findViewById(R.id.BMRBackButton)
        bmrRecalc = findViewById(R.id.recalcBMR)

        heightButton.setOnClickListener{
            var selectedInches = spinnerInches.selectedItem.toString()


            heightString = selectedInches

            Log.d("Debug: ", "made it to updateHeight function")
            appDb.userDAO().updateHeight(heightString)

        }

        weightButton.setOnClickListener{
            var selectedWeight = spinnerPounds.selectedItem.toString()
            var weightInt = selectedWeight.toInt()


            Log.d("Debug: ", "made it to updateWeight function")
            appDb.userDAO().updateWeight(weightInt)

        }

        levelButton.setOnClickListener{
            var selectedLevel = spinnerLevel.selectedItem.toString()
            Log.d("Debug: ", "made it to updateLevel function")
            appDb.userDAO().updateLevel(selectedLevel)

        }

        backButton.setOnClickListener{
            val intent = Intent(this, ProfileHomePage::class.java).apply{
            }
            startActivity(intent)

        }

        bmrRecalc.setOnClickListener{
            appDb.userDAO().updateBMR(calculateCalories())

        }



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


}