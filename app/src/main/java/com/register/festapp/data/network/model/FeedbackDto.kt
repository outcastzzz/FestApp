package com.register.festapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedbackDto(
    @Json(name = "count") val count: Int,
    @Json(name = "rating") val rating: Double
)