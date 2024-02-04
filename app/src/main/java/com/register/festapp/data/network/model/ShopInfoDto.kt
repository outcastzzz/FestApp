package com.register.festapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopInfoDto(
    @Json(name = "items") val items: List<ItemDto>
)
