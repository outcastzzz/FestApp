package com.register.festapp.domain.entities

import com.squareup.moshi.Json

data class Price(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)