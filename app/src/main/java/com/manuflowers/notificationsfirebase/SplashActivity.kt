package com.manuflowers.notificationsfirebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var mainIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_splash)

        //Prepare data from background notification
        prepareDataFromNotification()
        goToMainActivity()
    }

    private fun prepareDataFromNotification() {

        val intent = intent

        mainIntent = Intent(this, MainActivity::class.java)

        if (intent != null && intent.extras != null && intent.hasExtra("title")) {

            mainIntent.putExtra("modal", true)

            if (!intent.getStringExtra("title").trim { it <= ' ' }.isEmpty()) {
                mainIntent.putExtra(
                    "title",
                    intent.getStringExtra("title")
                )
            }

            if (intent.hasExtra("description") && !intent.getStringExtra("description").trim { it <= ' ' }.isEmpty()) {
                mainIntent.putExtra(
                    "description",
                    intent.getStringExtra("description")
                )
            }

            if (intent.hasExtra("image") && !intent.getStringExtra("image").trim { it <= ' ' }.isEmpty()) {
                mainIntent.putExtra(
                    "image",
                    intent.getStringExtra("image")
                )
            }

            if (intent.hasExtra("link") && !intent.getStringExtra("link").trim { it <= ' ' }.isEmpty()) {
                mainIntent.putExtra(
                    "link",
                    intent.getStringExtra("link")
                )
            }
        }
    }

    private fun goToMainActivity(){
        startActivity(mainIntent)
        finish()
    }
}