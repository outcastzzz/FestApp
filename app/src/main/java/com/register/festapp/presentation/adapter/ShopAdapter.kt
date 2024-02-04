package com.register.festapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.register.festapp.R
import com.register.festapp.databinding.ShopItemBinding
import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.presentation.MainViewModel
import java.util.Locale

class ShopAdapter(
    private val viewModel: MainViewModel
): ListAdapter<ShopItem, ShopItemViewHolder>(DiffUtilItemCallback) {

    private var originalList: List<ShopItem> = listOf()
    private var filteredList: List<ShopItem> = listOf()

    private var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val binding = ShopItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        with(holder.binding) {
            when(shopItem.id) {
                "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_blade, R.drawable.ic_shop_item_gel)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "54a876a5-2205-48ba-9498-cfecff4baa6e" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_soap, R.drawable.ic_shop_item_shampoo)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_gel, R.drawable.ic_shop_item_blade)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "16f88865-ae74-4b7c-9d85-b68334bb97db" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_oil, R.drawable.ic_shop_item_mask)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "26f88856-ae74-4b7c-9d85-b68334bb97db" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_shampoo, R.drawable.ic_shop_item_oil)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "15f88865-ae74-4b7c-9d81-b78334bb97db" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_blade, R.drawable.ic_shop_item_soap)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "88f88865-ae74-4b7c-9d81-b78334bb97db" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_mask, R.drawable.ic_shop_item_oil)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
                "55f58865-ae74-4b7c-9d81-b78334bb97db" -> {
                    val imageSliderAdapter = ImageSliderAdapter(
                        listOf(R.drawable.ic_shop_item_soap, R.drawable.ic_shop_item_gel)
                    )
                    viewPagerImageSlider.adapter = imageSliderAdapter
                }
            }
            btnAddFavourite.setOnClickListener {
                if (btnAddFavourite.tag == "unliked") {
                    btnAddFavourite.setImageResource(R.drawable.ic_item_liked)
                    btnAddFavourite.tag = "liked"
                    shopItem.isFavourite = true
                    viewModel.addItemToFavourites(shopItem)
                } else {
                    btnAddFavourite.setImageResource(R.drawable.ic_profile_heart)
                    btnAddFavourite.tag = "unliked"
                    shopItem.isFavourite = false
                    viewModel.removeItemFromFavourites(shopItem)
                }
            }
            TabLayoutMediator(holder.binding.tabDots, holder.binding.viewPagerImageSlider) { _, _ -> }.attach()
            titleTv.text = shopItem.title
            val priceStr = shopItem.price.price + " " + shopItem.price.unit
            priceTv.text = priceStr
            val priceWithDiscStr = shopItem.price.priceWithDiscount + " " + shopItem.price.unit
            priceWithDiscount.text = priceWithDiscStr
            val discStr = shopItem.price.discount.toString() + " %"
            discountTv.text = discStr
            descriptionTv.text = shopItem.subtitle
            tvRating.text = shopItem.feedback.rating.toString()
            val feedbackCountStr = "(${shopItem.feedback.count})"
            tvCountFeedback.text = feedbackCountStr

            holder.itemView.setOnClickListener {
                clickListener?.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}