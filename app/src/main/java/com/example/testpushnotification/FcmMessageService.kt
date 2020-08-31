package com.example.testpushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 *Created by Ankit Bajaj on 27-08-2020.
 */
class FcmMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // We can update token to server
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")



        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val CHANNEL_ID = "my_channel_01"
            val name = "my_channel"
            val Description = "This is my channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = Description
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mChannel.setShowBadge(false)
            notificationManager.createNotificationChannel(mChannel)
        }


        val intent = Intent(this, PushNotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // val stackBuilder =  TaskStackBuilder.create(this)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val noticationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, "my_channel_01")
        noticationBuilder.setContentTitle("Ankit")
        noticationBuilder.setContentText("Bajaj")
        noticationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        noticationBuilder.setAutoCancel(true)
        noticationBuilder.setContentIntent(pendingIntent)


        notificationManager.notify(0  /*ID of notification*/ , noticationBuilder.build())
    }

    companion object {
        private const val TAG = "FcmMessageService"
    }

}