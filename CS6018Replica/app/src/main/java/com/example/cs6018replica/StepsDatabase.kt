package com.example.cs6018replica

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [StepCounter :: class], version = 1, exportSchema = true)
abstract class StepsDatabase : RoomDatabase() {

    abstract fun stepCounterDAO() : StepCounterDAO

    companion object{
        @Volatile
        private var INSTANCE : StepsDatabase? = null

        fun getDatabase(context : Context): StepsDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

//            val MIGRATION_1_2 = object : Migration(1, 2) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL("ALTER TABLE user_table ADD COLUMN location STRING")
//                }
//            }





            synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        StepsDatabase::class.java,
                        "step_counter_database"
                    ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}