package com.register.festapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoDto(
    @Json(name = "title") val title: String?,
    @Json(name = "value") val value: String?
)
