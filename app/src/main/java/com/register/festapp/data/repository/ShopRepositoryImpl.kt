package com.register.festapp.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.register.festapp.data.database.AppDatabase
import com.register.festapp.data.database.ShopDao
import com.register.festapp.data.database.UserDataDbModel
import com.register.festapp.data.mapper.ShopMapper
import com.register.festapp.data.network.ApiService
import com.register.festapp.data.network.model.FeedbackDto
import com.register.festapp.data.network.model.InfoDto
import com.register.festapp.data.network.model.ItemDto
import com.register.festapp.data.network.model.PriceDto
import com.register.festapp.data.network.model.ShopInfoDto
import com.register.festapp.domain.entities.Feedback
import com.register.festapp.domain.entities.Info
import com.register.festapp.domain.entities.Price
import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.domain.entities.UserData
import com.register.festapp.domain.repository.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val mapper: ShopMapper,
    private val apiService: ApiService,
    private val dao: ShopDao,
): ShopRepository {

    override suspend fun getShopItemList(): List<ShopItem> {
        val response = apiService.getShopInfo()
        return when(response.code()) {
            in 200..400 -> {
                return response.body()?.items?.map { it.toShopItem() } ?: emptyList()
            }
            in 400..500 -> emptyList()
            else -> emptyList()
        }
    }

    override suspend fun saveUserData(data: UserData) {
        dao.insertData(mapper.mapUserEntityToDbModel(data))
    }

    override suspend fun getUserData(): UserData {
        val data = dao.getUserData()
        return data?.toEntity() ?: UserData("", "", "")
    }

    private fun PriceDto.toPrice(): Price {
        return Price(
            price = this.price,
            discount = this.discount,
            priceWithDiscount = this.priceWithDiscount,
            unit = this.unit
        )
    }

    private fun FeedbackDto.toFeedback(): Feedback {
        return Feedback(
            count = this.count,
            rating = this.rating
        )
    }

    private fun InfoDto.toInfo(): Info {
        return Info(
            title = this.title,
            value = this.value
        )
    }

    private fun ItemDto.toShopItem(): ShopItem {
        val price = this.price.toPrice()
        val feedback = this.feedback.toFeedback()
        val infoList = this.info.map { it?.toInfo() }

        return ShopItem(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            price = price,
            feedback = feedback,
            tags = this.tags,
            available = this.available,
            description = this.description,
            info = infoList,
            ingredients = this.ingredients
        )
    }

    private fun UserDataDbModel.toEntity(): UserData {
        return UserData(
            name = this.name,
            surname = this.surname,
            phoneNumber = this.phoneNumber
        )
    }

}