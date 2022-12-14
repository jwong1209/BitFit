package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface EntryDao {
    @Query("SELECT * FROM entity_table")
    fun getAll(): Flow<List<EntryEntity>>

    @Insert
    fun insertAll(entries: List<EntryEntity>)

    @Insert
    fun insert(entry: EntryEntity)

    @Query("DELETE FROM entity_table")
    fun deleteAll()

    @Query("SELECT AVG(calorie) FROM entity_table")
    fun getAvg(): Float

    @Query("SELECT MIN(calorie) FROM entity_table")
    fun getMin(): Float

    @Query("SELECT MAX(calorie) FROM entity_table")
    fun getMax(): Float

}