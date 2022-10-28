package com.example.a6018appandroid

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.a6018appandroid.R
import com.example.a6018appandroid.databinding.ActivityMainBinding
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appDb : AppDatabase
    var clickCount = 0;
    private lateinit var userPhoto : ImageView
    private lateinit var calorieText : TextView
    private lateinit var calorieOutputText : TextView
    private lateinit var stepsValue : TextView
    //private lateinit var bmrPage : BMRcalculation
    var running = false
    var sensorManager:SensorManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            appDb = AppDatabase.getDatabase(this)
            sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        userPhoto = findViewById(R.id.userPhoto)

        calorieText = findViewById(R.id.targetCalorieText)
        calorieOutputText = findViewById(R.id.targetCalorieOutput)


        calorieText.text = "Your Target Daily Calorie Intake: "
        calorieOutputText.text = appDb.userDAO().getBMR().toString()

        //printing all the sensors

        val sensorList = sensorManager!!.getSensorList(Sensor.TYPE_ALL)
        sensorList.forEachIndexed { index, sensor ->
            Log.d("Debug: ","Sensor (${index + 1}): ${sensor.name}; isWakeUpSensor: ${sensor.isWakeUpSensor}")
        }

        //checking the permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug: ", "Permission for activity not granted")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val ACTIVITY_RECOGNITION_REQUEST_CODE = 100
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    ACTIVITY_RECOGNITION_REQUEST_CODE
                    
                )
            }
        }


        userPhoto.setImageBitmap(BitmapFactory.decodeFile(appDb.userDAO().getPicture()))



        userPhoto.setOnClickListener{
            if(appDb.userDAO().getAll() == null){
                Toast.makeText(this, "Create a profile first!", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, ProfileHomePage::class.java).apply{
                    "Welcome!"
                }
                startActivity(intent)

            }


        }




    }

    fun startProfile( view: View) {
        if(clickCount == 0 && appDb.userDAO().getAll() != null){
            Toast.makeText(this, "Are you sure you want to start over?", Toast.LENGTH_SHORT).show()
            clickCount++
        }
        else{
            appDb.userDAO().deleteAll()
            val intent = Intent(this, Username::class.java).apply{
            }
            startActivity(intent)

        }


    }


    override fun onResume() {
        super.onResume()
        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(this, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {

        if (running) {
            stepsValue.text = "" + event.values[0]
        }
    }



}


