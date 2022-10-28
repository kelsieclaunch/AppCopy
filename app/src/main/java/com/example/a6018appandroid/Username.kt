package com.example.a6018appandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a6018appandroid.databinding.ActivityMainBinding
import com.example.a6018appandroid.databinding.ActivityUsernameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Username : AppCompatActivity() {

    private lateinit var binding : ActivityUsernameBinding
    private lateinit var appDb : AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)


        binding.enterNameButton.setOnClickListener(){
            writeToDb()

        }
    }

    fun enterNameButton() {
        val intent = Intent(this, Age::class.java).apply{
            "In Age class"
        }
        startActivity(intent)

    }

    fun writeToDb() {

        val username = binding.editTextTextPersonName.text.toString()

        if (username.isNotEmpty()){
            val user = User(1, username, null, null, null, null, null, null, null, null)

            GlobalScope.launch(Dispatchers.IO){

                appDb.userDAO().insert(user)
            }
            binding.editTextTextPersonName.text.clear()
            Toast.makeText(this@Username, "Name entered successfully!", Toast.LENGTH_SHORT).show()
            enterNameButton()

        }
        else{
            Toast.makeText(this@Username, "Please enter your name", Toast.LENGTH_SHORT).show()

        }


    }
}