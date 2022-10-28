package com.example.cs6018replica

import com.google.android.gms.location.*
import android.annotation.SuppressLint
import android.app.Activity
import android.app.TaskStackBuilder.create
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase.create
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.media.audiofx.AcousticEchoCanceler.create
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.app.TaskStackBuilder.create
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.EdgeEffectCompat.create
import androidx.savedstate.SavedStateRegistryController.Companion.create
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationResult.create
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY

import org.w3c.dom.Text
import java.lang.NullPointerException
import java.util.*
import java.util.jar.Manifest


class Location : AppCompatActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest : LocationRequest
    private val PERMISSION_ID = 1000

    private lateinit var appDb : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        appDb = AppDatabase.getDatabase(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


    }
    fun nextLocationButton( view: View) {
        val intent = Intent(this, Picture::class.java).apply{
            "In Picture class"
        }
        startActivity(intent)

    }

    fun onClickLocation(view : View ){
        Log.d("Debug:", CheckPermission().toString())
        Log.d("Debug:", isLocationEnabled().toString())
        RequestPermission()
        getLastLocation()

    }

    private fun CheckPermission():Boolean{
        if(
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==PackageManager.PERMISSION_GRANTED

        ){
            return true
        }
        return false
    }

    private fun RequestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean{
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }




    fun getLastLocation() {
        val outputText: TextView? = findViewById<TextView?>(R.id.locationOutput)
        //outputText?.text = "Your current location is: "
        Log.d("Debug: ", "made it into getLastLocation function")
        if (CheckPermission()) {
            if (isLocationEnabled()) {
                Log.d("Debug: ", "made it past if statements")
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ){
                    RequestPermission()
                }

                fusedLocationProviderClient.getLastLocation().addOnCompleteListener { task ->
                    var location = task.result

                    Log.d("Debug: ", "Location: " + location.toString())


                    Log.d("Debug: ", "made it to updateLocation function")
                    var locationString = (location.latitude.toString()) + "," + (location.longitude.toString())
                    appDb.userDAO().updateLocation(locationString)




                    if (location == null) {
                        NewLocationData()
                    } else {
                        Log.d("Debug:", "Your Location:" + location.longitude)
                        val currentLocText = "Your current location is: " + getCityName(location.latitude, location.longitude)

                        outputText?.text = currentLocText
                    }
                }


            } else {
                Toast.makeText(this, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
            }

        } else {
            RequestPermission()

        }
    }

    private fun NewLocationData() {
        locationRequest = LocationRequest.create().apply {
            priority = Priority.PRIORITY_HIGH_ACCURACY
            interval = 0
            fastestInterval = 0
            numUpdates = 1
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            RequestPermission()
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )

    }


    private val locationCallback = object : LocationCallback(){
        @SuppressLint("SetTextI18n")
        override fun onLocationResult(locationResult: LocationResult) {
            val outputText: TextView? = findViewById<TextView?>(R.id.locationOutput)
            var lastLocation: Location? = locationResult.lastLocation
            Log.d("Debug:","your last last location: "+ lastLocation?.longitude.toString())
            outputText?.text = "You Last Location is : Long: "+ lastLocation?.longitude + " , Lat: " + lastLocation?.latitude + "\n" + getCityName(lastLocation!!.latitude,lastLocation!!.longitude)
        }
    }


    private fun getCityName(lat: Double,long: Double):String{
        var cityName:String = ""
        var countryName = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Address = geoCoder.getFromLocation(lat,long,3)

        cityName = Address?.get(0)!!.locality
        countryName = Address.get(0).countryName
        Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName)
        return "City: $cityName; Country: $countryName"
    }

}