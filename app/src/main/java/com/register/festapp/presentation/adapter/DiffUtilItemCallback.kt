package com.register.festapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.register.festapp.domain.entities.ShopItem

object DiffUtilItemCallback: DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.tags == newItem.tags
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}