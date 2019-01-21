package com.manuflowers.notificationsfirebase

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {

        var refreshedToken = FirebaseInstanceId.getInstance().token
        Log.e("M- TOKEN", "Refreshed token: $refreshedToken")
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private fun sendRegistrationToServer(token: String?) {

    }
}