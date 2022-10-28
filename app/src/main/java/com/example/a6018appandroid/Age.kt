package com.example.a6018appandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast

class Age : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var spinner : Spinner




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        appDb = AppDatabase.getDatabase(this)
        spinner = findViewById(R.id.spinner2)

    }

    fun buttonActivityNext( view: View) {
        var selectedAge = spinner.selectedItem.toString()
        var ageInt = selectedAge.toInt()
        //Toast.makeText(this, ageInt, Toast.LENGTH_SHORT).show()


        Log.d("Debug: ", "made it to updateAge function")
        appDb.userDAO().updateAge(ageInt)

        val intent = Intent(this, Sex::class.java).apply{
            "In Age class"
        }
        startActivity(intent)

    }
}