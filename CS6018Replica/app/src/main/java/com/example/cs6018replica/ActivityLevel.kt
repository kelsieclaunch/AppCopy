package com.example.cs6018replica

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner

class ActivityLevel : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        appDb = AppDatabase.getDatabase(this)
        spinner = findViewById(R.id.spinner)
    }

    fun buttonActivityNext( view: View) {
        var selectedLevel = spinner.selectedItem.toString()
        Log.d("Debug: ", "made it to updateLevel function")
        appDb.userDAO().updateLevel(selectedLevel)
        val intent = Intent(this, com.example.cs6018replica.Location::class.java).apply{
            "In Location page"
        }
        startActivity(intent)

    }


}