package com.mwaibanda.momentum.android

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.mwaibanda.momentum.android.di.mainModule
import com.mwaibanda.momentum.android.di.viewModelModule
import com.mwaibanda.momentum.di.controllerModule
import com.mwaibanda.momentum.di.initKoin
import org.koin.android.ext.koin.androidContext

class MomentumApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.e(TAG, token)
        })
        initKoin {
            androidContext(this@MomentumApplication)
            modules(
                mainModule,
                controllerModule,
                viewModelModule
            )
        }

    }

    companion object {
        private const val TAG = "MomentumApplication"
    }
}