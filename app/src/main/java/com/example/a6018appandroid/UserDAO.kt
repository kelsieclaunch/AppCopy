package com.example.a6018appandroid

import androidx.room.*
import androidx.room.Dao


@Dao
interface UserDAO {

    // this is where our queries will go

    @Query("SELECT * FROM user_table LIMIT 1")
    fun getAll(): User
    //when we display all the users

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("UPDATE user_table SET age = :age WHERE id = 1")
    fun updateAge(age: Int)

    @Query("UPDATE user_table SET sex = :sex WHERE id = 1")
    fun updateSex(sex: String)

    @Query("UPDATE user_table SET weight = :weight WHERE id = 1")
    fun updateWeight(weight: Int)

    @Query("UPDATE user_table SET activity_level = :activity_level WHERE id = 1")
    fun updateLevel(activity_level: String)

    @Query("UPDATE user_table SET height = :height WHERE id = 1")
    fun updateHeight(height: String)

    @Query("UPDATE user_table SET picture = :picture WHERE id = 1")
    fun updatePicture(picture: String)

    @Query("UPDATE user_table SET bmr = :bmr WHERE id = 1")
    fun updateBMR(bmr : Int )

    @Query("UPDATE user_table SET location = :location WHERE id = 1")
    fun updateLocation(location: String)

    @Query("SELECT username FROM user_table LIMIT 1")
    fun getName(): String

    @Query("SELECT age FROM user_table LIMIT 1")
    fun getAge(): Int

    @Query("SELECT sex FROM user_table LIMIT 1")
    fun getSex(): String

    @Query("SELECT weight FROM user_table LIMIT 1")
    fun getWeight(): Int

    @Query("SELECT height FROM user_table LIMIT 1")
    fun getHeight(): String

    @Query("SELECT activity_level FROM user_table LIMIT 1")
    fun getLevel(): String

    @Query("SELECT picture FROM user_table LIMIT 1")
    fun getPicture(): String

    @Query("SELECT bmr FROM user_table LIMIT 1")
    fun getBMR(): Int

    @Query("SELECT location FROM user_table LIMIT 1")
    fun getLocation(): String


//    @Update
//    suspend fun updateAllData(user: User)



    //we will need to add queries to allow the user to edit their information
}