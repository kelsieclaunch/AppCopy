package com.example.cs6018replica

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "username") @PrimaryKey(autoGenerate = false) val userName: String,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "activity_level") val activityLevel: String?,
    @ColumnInfo(name = "height") val height: String?,
    @ColumnInfo(name = "sex") val sex: String?,
    @ColumnInfo(name = "weight") val weight: Int?,
    @ColumnInfo(name = "picture") val picture: String?,
    @ColumnInfo(name ="bmr") val bmr: Int?,
    @ColumnInfo(name = "location") val location: String?
)