package com.manuflowers.notificationsfirebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("M- BODY", remoteMessage.notification!!.title.toString())
        val notificationManager = NotificationManagerCompat.from(this)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(this, "channel_default")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
                .setContentTitle(remoteMessage.notification!!.title)
                .setContentText(remoteMessage.notification!!.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)

        if (remoteMessage.data.isNotEmpty()) {
            val intent = Intent(this, SplashActivity::class.java)
            Log.e("M- messageId", "${remoteMessage.messageId}")
            Log.e("M- data", "${remoteMessage.data}")
            Log.e("M- notification", "${remoteMessage.notification}")
            Log.e("M- title", "${remoteMessage.notification!!.title}")
            Log.e("M- description", "${remoteMessage.data["description"]}")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("modal", true)
            intent.putExtra("title", remoteMessage.notification!!.title)
            if (!remoteMessage.data["description"].isNullOrBlank()) {
                intent.putExtra("description", remoteMessage.data["description"])
            }

            if (!remoteMessage.data["image"].isNullOrBlank()) {
                intent.putExtra("image", remoteMessage.data["image"])
            }

            if (!remoteMessage.data["link"].isNullOrBlank()) {
                intent.putExtra("link", remoteMessage.data["link"])
            }

            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT

            )

            notificationBuilder.setContentIntent(pendingIntent)
        }
        notificationManager.notify(remoteMessage.messageId!!.hashCode(), notificationBuilder.build())

        //old implementation :
        // sendNotification(remoteMessage.notification!!.body)
    }

    private fun sendNotification(body: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // NotificationCompat.Builder =
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this)
        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name)
        notificationBuilder.setContentTitle("Android")
        notificationBuilder.setContentText(body)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setSound(defaultSoundUri)
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}