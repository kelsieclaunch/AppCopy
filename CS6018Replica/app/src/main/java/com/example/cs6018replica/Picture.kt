package com.example.cs6018replica

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*



class Picture : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var button2: Button
    private lateinit var imageView2: ImageView
    private var mThumbnailImage: Bitmap? = null
    private var mDisplayIntent: Intent? = null


    companion object{
        val IMAGE_REQUEST_CODE = 100

    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        appDb = AppDatabase.getDatabase(this)

        button = findViewById(R.id.chooseFromGallery)
        imageView = findViewById(R.id.imageView1)

        button2 = findViewById(R.id.takeNewPhoto)
        imageView2 = findViewById(R.id.imageView2)


        button.setOnClickListener{
            if (!checkStoragePermission()) {
                requestStoragePermission()
            } else{
                pickImageFromGallery()
            }
        }
//
        button2.setOnClickListener {
            if (!checkCameraPermission()) {
                requestCameraPermission()
            } else {
                cameraLauncher
            }
        }

    }

    private fun checkCameraPermission(): Boolean{
        var res1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED
        var res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
        Log.d("Debug: ", "made it into checkCameraPermission function")

        return res1 && res2
    }

    private fun checkStoragePermission(): Boolean{
        var res2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
        Log.d("Debug: ", "made it into checkStoragePermission function")

        return res2
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestCameraPermission(){
        requestPermissions(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        Log.d("Debug: ", "made it into requestCameraPermission function")
        takeNewPicture()



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestStoragePermission(){
        Log.d("Debug: ", "made it into requestStoragePermission function")

        requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        pickImageFromGallery()


    }

    private fun pickImageFromGallery() {
        Log.d("Debug: ", "made it into pickImageFromGallery function")

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
        var pathString = intent.toString()
        appDb.userDAO().updatePicture(pathString)
    }


    private fun takeNewPicture() {
        Log.d("Debug: ", "made it into takeNewPicture function")

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try{
            cameraLauncher.launch(cameraIntent)
        } catch(ex:ActivityNotFoundException){
            Toast.makeText(this, "Camera currently unavailable", Toast.LENGTH_SHORT).show()

            // toast: camera currently unavailable, please choose from gallery
        }
    }

    private var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK){
            val extras = result.data!!.extras
            mThumbnailImage = extras!!["data"] as Bitmap?

            if(isExternalStorageWritable){
                val filePathString = saveImage(mThumbnailImage)
                mDisplayIntent?.putExtra("imagePath", filePathString)
            } else{
                Toast.makeText(this, "External storage not writable.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveImage(finalBitmap: Bitmap?): String{
        Log.d("Debug: ", "made it into saveImage function")

        val root = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val myDir = File("$root/saved_images")
        myDir.mkdirs()
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fname = "Thumbnail_$timeStamp.jpg"
        val file = File(myDir, fname)
        if(file.exists()) file.delete()
        try{
            val out = FileOutputStream(file)
            finalBitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            Toast.makeText(this, "file saved!", Toast.LENGTH_SHORT).show()
        } catch(e: Exception){
            e.printStackTrace()
        }
        var pathString = file.absolutePath.toString()
        appDb.userDAO().updatePicture(pathString)

        return file.absolutePath
    }

    private val isExternalStorageWritable: Boolean
        get(){
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
        }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("Debug: ", "made it into onActivityResult function")

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data)
        }
    }

    fun takeNewPhotoButton(view : View) {

        takeNewPicture()
        val image: ImageView? = findViewById<ImageView?>(R.id.imageView2)
        val bmp = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
        val stream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()
        saveImage(bmp)
        //image?.setImageDrawable( ContextCompat.getDrawable(applicationContext, com.google.android.material.R.drawable.test_custom_background))


    }

    fun profileHomeBtn( view: View) {
        val intent = Intent(this, ProfileHomePage::class.java).apply{
            val stream = ByteArrayOutputStream()
            val byteArray: ByteArray = stream.toByteArray()
            intent.putExtra("picture", byteArray)
        }
        startActivity(intent)

    }
}