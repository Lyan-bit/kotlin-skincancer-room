package com.example.skincancer

import android.app.Application
import androidx.room.Room

class SkincancerApplication : Application() {

    companion object {
        lateinit var database: SkincancerDatabase
            private set
    }
    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(
                this,
                SkincancerDatabase::class.java,
                "skincancerDatabase"
            )
            .build() }
}
