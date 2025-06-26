package com.example.zeroobserver

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nation_progress")
data class NationProgress(
    @PrimaryKey val country: String,
    val personality: String,
    val turnCount: Int,
    val history: String, // JSON 문자열
    val report: String
)

annotation class PrimaryKey

annotation class Entity(val tableName: String)
