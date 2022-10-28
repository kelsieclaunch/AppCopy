package com.example.cs6018replica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner

class Height : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var spinnerInches : Spinner

    private lateinit var resultString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)
        appDb = AppDatabase.getDatabase(this)
        spinnerInches = findViewById(R.id.spinner4)
        //spinnerInches = findViewById(R.id.spinner5)
    }
    fun heightNextButton( view: View) {
        var selectedInches = spinnerInches.selectedItem.toString()


        resultString = selectedInches

        Log.d("Debug: ", "made it to updateHeight function")
        appDb.userDAO().updateHeight(resultString)


        val intent = Intent(this, ActivityLevel::class.java).apply{
            "Activity Class"
        }
        startActivity(intent)

    }
}