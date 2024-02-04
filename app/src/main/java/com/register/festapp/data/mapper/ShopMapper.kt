package com.register.festapp.data.mapper

import com.register.festapp.data.database.UserDataDbModel
import com.register.festapp.data.network.model.FeedbackDto
import com.register.festapp.data.network.model.InfoDto
import com.register.festapp.data.network.model.PriceDto
import com.register.festapp.data.network.model.ShopInfoDto
import com.register.festapp.domain.entities.Feedback
import com.register.festapp.domain.entities.Info
import com.register.festapp.domain.entities.Price
import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.domain.entities.UserData
import javax.inject.Inject

class ShopMapper @Inject constructor() {

    fun mapUserEntityToDbModel(entity: UserData) = UserDataDbModel(
        name = entity.name,
        surname = entity.surname,
        phoneNumber = entity.phoneNumber
    )

}