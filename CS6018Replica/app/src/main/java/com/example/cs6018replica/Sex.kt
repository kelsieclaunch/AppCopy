package com.example.cs6018replica


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner

class Sex : AppCompatActivity() {
    private lateinit var spinner : Spinner
    private lateinit var appDb : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sex)
        appDb = AppDatabase.getDatabase(this)
        spinner = findViewById(R.id.spinnerSex)
    }
    fun enterSexButton( view: View) {

        var selectedSex = spinner.selectedItem.toString()

        //Toast.makeText(this, ageInt, Toast.LENGTH_SHORT).show()


        Log.d("Debug: ", "made it to updateSex function")
        appDb.userDAO().updateSex(selectedSex)
        val intent = Intent(this, Weight::class.java).apply{
            "In Weight class"
        }
        startActivity(intent)

    }
}