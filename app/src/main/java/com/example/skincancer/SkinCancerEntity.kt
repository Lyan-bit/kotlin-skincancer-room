package com.example.skincancer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skinCancerTable")
data class SkinCancerEntity (
    @PrimaryKey
    val id: String, 
    val dates: String, 
    val images: String, 
    val outcome: String
)
