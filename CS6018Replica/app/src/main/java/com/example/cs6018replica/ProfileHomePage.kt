package com.example.cs6018replica

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.cs6018replica.databinding.ActivityProfileHomePageBinding
import com.example.cs6018replica.databinding.ActivityUsernameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileHomePage : AppCompatActivity() {

    private lateinit var thisBinding : ActivityProfileHomePageBinding

    private lateinit var usernameBinding: ActivityUsernameBinding

    private lateinit var appDb : AppDatabase

    private lateinit var showName : TextView
    private lateinit var showAge : TextView
    private lateinit var showWeight : TextView
    private lateinit var showHeight : TextView
    private lateinit var showSex : TextView
    private lateinit var showActivityLevel : TextView
    private lateinit var showBMR : TextView

    private lateinit var showPhoto : ImageView

    private lateinit var editProfileButton : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thisBinding = ActivityProfileHomePageBinding.inflate(layoutInflater)
        usernameBinding = ActivityUsernameBinding.inflate(layoutInflater)
        setContentView(thisBinding.root)
        appDb = AppDatabase.getDatabase(this)
        //readFromDb()
        displayProfile()

        editProfileButton = findViewById(R.id.btnEditInfo)



        thisBinding.deleteProfile.setOnClickListener{
            GlobalScope.launch{
                appDb.userDAO().deleteAll()
            }
            val intent = Intent(this, MainActivity::class.java).apply{
                "In MainActivity class"
            }
            startActivity(intent)
        }

        editProfileButton.setOnClickListener{
            val intent = Intent(this, EditBMR::class.java).apply{
                //btnEditInfo

            }
            startActivity(intent)

        }




    }

    fun bmrButton (view : View){
        val intent = Intent(this, BMRcalculation::class.java).apply{

        }
        startActivity(intent)
    }



    fun WeatherButton( view: View) {

        val intent = Intent(this, Weather::class.java).apply{
            "In Weather class"
        }
        startActivity(intent)

    }

    fun HikeMapButton( view: View) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=hikes")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)

    }

    fun stepButton (view : View){
        val intent = Intent(this, StepCounterGesture::class.java).apply{

        }
        startActivity(intent)
    }

    fun displayProfile(){
        showName = findViewById(R.id.showName)
        showName.text = appDb.userDAO().getName()

        showAge = findViewById(R.id.showAge)
        val ageString = (appDb.userDAO().getAge().toString()) + " years"
        showAge.text = ageString

        showSex = findViewById(R.id.showSex)
        showSex.text = appDb.userDAO().getSex()

        showHeight = findViewById(R.id.showHeight)
        val heightString = (appDb.userDAO().getHeight()) + " inches"
        showHeight.text = heightString

        showActivityLevel = findViewById(R.id.showActivityLevel)
        showActivityLevel.text = appDb.userDAO().getLevel()

        showWeight = findViewById(R.id.showWeight)
        val weightString = (appDb.userDAO().getWeight().toString()) + " pounds"
        showWeight.text = weightString

        showBMR = findViewById(R.id.showBMR)
        if(appDb.userDAO().getBMR().toString() == "0"){
            val bmrString = "Please calculate BMR"
            showBMR.text = bmrString.toString()
        }
        else{
            val bmrString = (appDb.userDAO().getBMR().toString()) + " kcal/day"
            showBMR.text = bmrString
        }

        showPhoto = findViewById(R.id.imageViewProfile)
        showPhoto.setImageBitmap(BitmapFactory.decodeFile(appDb.userDAO().getPicture()))


//        withContext(Dispatchers.Main){
//            thisBinding.showName.text = user.userName
//            thisBinding.showActivityLevel.text = user.activityLevel
//            thisBinding.showAge.text = user.age.toString()
//            thisBinding.showSex.text = user.sex
//            thisBinding.showHeight.text = user.height
//            thisBinding.showWeight.text = user.weight.toString()
//
//        }
    }



//    fun readFromDb(){
//
//        val profile = usernameBinding.editTextTextPersonName.text.toString()
//
//        if(profile.isNotEmpty()){
//            lateinit var user : User
//            GlobalScope.launch{
//                user = appDb.userDAO().getAll()
//                displayProfile()
//
//            }
//
//        }
//
//    }
}