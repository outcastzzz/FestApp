package com.register.festapp.domain.useCases

import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.domain.repository.ShopRepository
import javax.inject.Inject

class GetShopItemListUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getShopItemList(): List<ShopItem> = repository.getShopItemList()

}