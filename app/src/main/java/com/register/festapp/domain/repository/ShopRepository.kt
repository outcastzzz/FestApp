package com.register.festapp.domain.repository

import android.app.Application
import android.content.Context
import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.domain.entities.UserData

interface ShopRepository {

    suspend fun getShopItemList(): List<ShopItem>

    suspend fun saveUserData(data: UserData)

    suspend fun getUserData(): UserData

}