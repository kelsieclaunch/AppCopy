package com.example.cs6018replica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cs6018replica.databinding.ActivityWeatherBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection


class Weather : AppCompatActivity()
{
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var appDb : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)

        fetchWeatherData().start()
    }

    fun backToProfile( view: View) {

        val intent = Intent(this, ProfileHomePage::class.java).apply{
            "ProfileHomepage Class"
        }
        startActivity(intent)

    }

    private fun fetchWeatherData(): Thread {

        var coords: String = appDb.userDAO().getLocation()
        val strs = coords.split(",").toTypedArray()
        Log.d("Debug:", strs[0])
        Log.d("str 1:", strs[1])
        return Thread {
            val url =
                URL("https://api.openweathermap.org/data/2.5/weather?appid=0dffd2d3ce10cd987ed4bec9559e30f8&units=imperial&lat=" + strs[0] + "&lon=" + strs[1])
            val connection = url.openConnection() as HttpsURLConnection
            Log.d("URL:", url.toString())


            if (connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, ApiRequest::class.java)
                updateUI(request)
                inputStreamReader.close()
                inputSystem.close()
            } else {
                binding.weatherDescriptions.text = "Failed Connection"
            }
        }

    }

    private fun updateUI(request: ApiRequest)
    {
        runOnUiThread {
            kotlin.run {
                binding.name.text = request.name
                binding.region.text = request.wind.speed.toString() + "mph"
                binding.temperature.text = request.main.temp.toString() + "\u2109"
                binding.weatherDescriptions.text = request.weather[0].description
            }
        }
    }
}