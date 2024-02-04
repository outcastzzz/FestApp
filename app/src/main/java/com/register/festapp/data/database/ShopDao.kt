package com.register.festapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(userDataDbModel: UserDataDbModel)

    @Query("SELECT * FROM user_data_table ORDER BY phoneNumber DESC")
    fun getUserData(): UserDataDbModel?

}