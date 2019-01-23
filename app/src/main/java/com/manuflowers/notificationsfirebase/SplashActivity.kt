package com.manuflowers.notificationsfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class SplashActivity : AppCompatActivity() {
    private lateinit var mainIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_splash)

        val gson = Gson()

        val intent = intent
        if (intent?.extras != null && intent.hasExtra("title")) {
            Log.e("M- title", intent.getStringExtra("title")!!.trim())
        }

        if (intent.extras != null) {
            Log.e("M-intent","${intent.hasExtra("title")}")

            //intent.getStringExtra("title").trim()
            for ( i in intent?.extras!!.keySet()) {

                val value = intent?.extras!!.getString(i)
                Log.e("M-","$i: $value")
            //    Log.e("M- que es i ","$i ")
              //  Log.e("M-value","${value to String()}")

            }
        }

    }

    private fun goToMainActivity(){
        startActivity(mainIntent)
        finish()
    }
}