package com.example.testpushnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 *Created by Ankit Bajaj on 28-08-2020.
 */
class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
    }
}