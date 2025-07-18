package com.example.zeroobserver

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NationProgressDao {
    @Query("SELECT * FROM nation_progress")
    suspend fun getAll(): List<NationProgress>

    @Query("SELECT * FROM nation_progress WHERE country = :country")
    suspend fun getByCountry(country: String): NationProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(progress: NationProgress)

    @Delete
    suspend fun delete(progress: NationProgress)
}
