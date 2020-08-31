package com.example.testpushnotification

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 *Created by Ankit Bajaj on 28-08-2020.
 */

class FCMSilentService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // We can update token to server
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        remoteMessage.notification?.let {
            silentPushMethods(it.title)
        }
    }

    private fun silentPushMethods(data: String?) {

//        val silentValue = data.getString("6")

        if (data != null && !data.equals("", true)) {
            val intent = Intent(this, MyBroadcastReceiver::class.java)
            intent.action = "com.push.BroadCastReceiver"
            intent.putExtra("action", data)
            sendBroadcast(intent)
        }
    }

    fun redirectToBroadCastReceiver() {

    }

    companion object {
        private const val TAG = "FCMSilentService"
    }
}