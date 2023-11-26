package com.mad.tusmybuddyAMv1

import android.app.Application
import com.google.firebase.FirebaseApp

class TusMyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}