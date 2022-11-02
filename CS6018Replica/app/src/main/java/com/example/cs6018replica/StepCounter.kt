package com.example.cs6018replica

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "steps_table")
data class StepCounter(
    @ColumnInfo(name = "numSteps") val Steps: Int?,
    @ColumnInfo(name = "date") @PrimaryKey(autoGenerate = false) val Date: String
)