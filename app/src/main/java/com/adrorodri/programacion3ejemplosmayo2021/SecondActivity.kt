package com.adrorodri.programacion3ejemplosmayo2021

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraintlayout)
        Log.d("LIFECYCLE", "onCreate SecondActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart SecondActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume SecondActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause SecondActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop SecondActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy SecondActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LIFECYCLE", "onRestart SecondActivity")
    }
}