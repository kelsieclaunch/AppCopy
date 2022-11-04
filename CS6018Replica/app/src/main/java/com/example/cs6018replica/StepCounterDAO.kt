package com.example.cs6018replica

import androidx.room.*
import androidx.room.Dao


@Dao
interface StepCounterDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(stepCounter: StepCounter)

    @Query("DELETE FROM steps_table")
    fun deleteAll()

    @Query("SELECT numSteps FROM steps_table where date = 'yesterday'")
    fun getYesterdaySteps(yesterday : String?)


    // this is where our queries will go

}