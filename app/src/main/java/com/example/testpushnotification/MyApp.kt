package com.example.testpushnotification

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

/**
 *Created by Ankit Bajaj on 27-08-2020.
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        fetchFCMToken()
    }

    private fun fetchFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    //FirebaseCrashlytics.getInstance().recordException(Throwable(task.exception))
                    Log.i("MyApp", "Fetch Token failed")
                    return@OnCompleteListener
                } else {
                    val token = task.result?.token
                    Log.i("MyApp", "Fetch Token Success $token")
                }
                // Get new Instance ID token

                //  sendTokenToServer(token)
            })
    }
}