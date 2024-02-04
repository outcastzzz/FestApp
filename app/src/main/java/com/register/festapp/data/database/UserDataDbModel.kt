package com.register.festapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class UserDataDbModel(
    val name: String,
    val surname: String,
    @PrimaryKey
    val phoneNumber: String
)