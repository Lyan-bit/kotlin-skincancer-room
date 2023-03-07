package com.example.skincancer

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(SkinCancerEntity::class)], version = 1, exportSchema = false)
abstract class SkincancerDatabase : RoomDatabase() {
    abstract fun skinCancerDao(): SkinCancerEntityDao
}
