package com.bcampa.roomdemo.database

import android.content.Context
import androidx.room.*
import com.bcampa.roomdemo.dao.UserDao
import com.bcampa.roomdemo.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: UserDatabase? = null

        fun getInstance(context: Context) : UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                        context,
                        UserDatabase::class.java, "userdatabase.db"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as UserDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}

