package com.bcampa.roomdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String,
    var email: String,
    var password: String,
    var cpf: String
) {
    constructor(name: String, email: String, password: String, cpf: String):
            this(null, name, email, password, cpf)

    override fun toString(): String {
        return "$id - $name - $email"
    }
}