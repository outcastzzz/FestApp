package com.register.festapp.domain.entities

data class ShopItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: Price,
    val feedback: Feedback,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<Info?>,
    val ingredients: String,
    var isFavourite: Boolean = false
)