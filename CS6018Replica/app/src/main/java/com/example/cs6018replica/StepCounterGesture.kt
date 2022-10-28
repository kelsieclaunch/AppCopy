package com.example.cs6018replica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat

class StepCounterGesture : AppCompatActivity(), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    private lateinit var stepCounter : TextView

    private lateinit var mDetector: GestureDetectorCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_step_gesture)

        mDetector = GestureDetectorCompat(this, this)
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        Log.d("Debug: ", "onSingleTapConfirmed: event")
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d("Debug: ", "onDoubleTap: $event")
        stepCounter = findViewById(R.id.stepCounterGesture)
        stepCounter.text = "Hi!"

        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        Log.d("Debug: ", "onDoubleTapEvent: event")
        return true
    }


    fun onBackButton(view : View) {
        val intent = Intent(this, ProfileHomePage::class.java ). apply{

        }
        startActivity(intent)

    }

    override fun onDown(p0: MotionEvent): Boolean {
        Log.d("Debug: ", "onSingleTapConfirmed: event")
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        Log.d("Debug: ", "onSingleTapConfirmed: event")

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        Log.d("Debug: ", "onSingleTapConfirmed: event")
        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        Log.d("Debug: ", "onSingleTapConfirmed: event")
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        Log.d("Debug: ", "onSingleTapConfirmed: event")

    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        Log.d("Debug: ", "onSingleTapConfirmed: event")
        return true
    }
}
