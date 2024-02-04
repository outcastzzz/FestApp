package com.register.festapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDto(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "subtitle") val subtitle: String,
    @Json(name = "price") val price: PriceDto,
    @Json(name = "feedback") val feedback: FeedbackDto,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "available") val available: Int,
    @Json(name = "description") val description: String,
    @Json(name = "info") val info: List<InfoDto?>,
    @Json(name = "ingredients") val ingredients: String
)