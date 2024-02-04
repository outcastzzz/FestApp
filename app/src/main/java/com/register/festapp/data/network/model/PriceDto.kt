package com.register.festapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PriceDto(
    @Json(name = "price") val price: String,
    @Json(name = "discount") val discount: Int,
    @Json(name = "priceWithDiscount") val priceWithDiscount: String,
    @Json(name = "unit") val unit: String
)