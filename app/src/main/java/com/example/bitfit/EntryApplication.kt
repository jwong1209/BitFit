package com.example.bitfit

import android.app.Application

class EntryApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}