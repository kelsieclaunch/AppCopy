package com.example.cs6018replica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner

class Weight : AppCompatActivity() {
    private lateinit var spinner : Spinner
    private lateinit var appDb : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
        appDb = AppDatabase.getDatabase(this)
        spinner = findViewById(R.id.spinner3)
    }

    fun weightNextButton( view: View) {
        var selectedWeight = spinner.selectedItem.toString()
        var weightInt = selectedWeight.toInt()
        //Toast.makeText(this, ageInt, Toast.LENGTH_SHORT).show()


        Log.d("Debug: ", "made it to updateWeight function")
        appDb.userDAO().updateWeight(weightInt)

        val intent = Intent(this, Height::class.java).apply{
            "In height class"
        }
        startActivity(intent)

    }
}
