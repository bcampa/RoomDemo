package com.bcampa.roomdemo.dao

import androidx.room.*
import br.edu.infnet.roomapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users : User)

    @Update
    fun updateUsers(vararg users: User)

    @Delete
    fun deleteUsers(vararg users: User)

    @Query("SELECT * FROM users")
    fun loadAllUsers() : List<User>

    @Query("DELETE FROM users")
    fun deleteAllUsers()
}