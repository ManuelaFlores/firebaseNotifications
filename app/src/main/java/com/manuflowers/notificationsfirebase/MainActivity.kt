package com.manuflowers.notificationsfirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.manuflowers.notificationsfirebase.utils.TAG_CONTACT_DIALOG
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initModalNotification()

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                //    Log.w(FragmentActivity.TAG, "getInstanceId failed", task.exception)
                    Log.e("M- getInstanceId fail","$task.exception")
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result.token

                // Log and toast
                val msg =  "este es el token $token"
               // Log.d(FragmentActivity.TAG, msg)
                Log.e("M- token", "este es -> $msg ")
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            })
        // [END retrieve_current_token]
    }



    private fun initModalNotification() {

        if (intent.hasExtra("modal")) {
            makeNotificationModal(
                if (intent.hasExtra("title")) {
                    intent.getStringExtra("title")
                } else {
                    ""
                },
                if (intent.hasExtra("description")) {
                    intent.getStringExtra("description")
                } else {
                    ""
                },
                if (intent.hasExtra("image")) {
                    intent.getStringExtra("image")
                } else {
                    ""
                },
                if (intent.hasExtra("link")) {
                    intent.getStringExtra("link")
                } else {
                    ""
                }
            )
        }
    }

    private fun makeNotificationModal(
        title: String,
        description: String,
        image: String,
        link: String
    ) {

        val modalNotificationFragment = NotificationModal
            .newInstance(title, image, description, link)

        modalNotificationFragment.show(supportFragmentManager, TAG_CONTACT_DIALOG)
    }
}
