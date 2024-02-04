package com.register.festapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.StrikethroughSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.register.festapp.R
import com.register.festapp.databinding.FragmentShopItemInfoBinding
import com.register.festapp.presentation.MainViewModel
import com.register.festapp.presentation.ShoppingApp
import com.register.festapp.presentation.ViewModelFactory
import com.register.festapp.presentation.adapter.ImageSliderAdapter
import javax.inject.Inject

class ShopItemInfoFragment : Fragment() {

    private var _binding: FragmentShopItemInfoBinding? = null
    private val binding: FragmentShopItemInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemInfoBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    private val component by lazy {
        (requireActivity().application as ShoppingApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopItemInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        observeViewModel()
        setupHideBtn()
        binding.ivMoveBack.setOnClickListener {
            setupMoveBack()
        }
    }

    private fun observeViewModel() {
        val ratingBar = binding.ratingBar3
        viewModel.shopItem.observe(viewLifecycleOwner) {
            with(binding) {
                when(it.id) {
                    "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_blade, R.drawable.ic_shop_item_gel)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "54a876a5-2205-48ba-9498-cfecff4baa6e" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_soap, R.drawable.ic_shop_item_shampoo)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_gel, R.drawable.ic_shop_item_blade)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "16f88865-ae74-4b7c-9d85-b68334bb97db" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_oil, R.drawable.ic_shop_item_mask)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "26f88856-ae74-4b7c-9d85-b68334bb97db" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_shampoo, R.drawable.ic_shop_item_oil)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "15f88865-ae74-4b7c-9d81-b78334bb97db" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_blade, R.drawable.ic_shop_item_soap)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "88f88865-ae74-4b7c-9d81-b78334bb97db" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_mask, R.drawable.ic_shop_item_oil)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                    "55f58865-ae74-4b7c-9d81-b78334bb97db" -> {
                        val imageSliderAdapter = ImageSliderAdapter(
                            listOf(R.drawable.ic_shop_item_soap, R.drawable.ic_shop_item_gel)
                        )
                        viewPagerSliderBig.adapter = imageSliderAdapter
                    }
                }
                TabLayoutMediator(tabDotsBig, viewPagerSliderBig) { _, _ -> }.attach()
                tvTitle.text = it.title
                tvSubtitle.text = it.subtitle
                availableTv.text = "Доступно для заказа ${it.available} штук"
                feedbackCountTv.text = it.feedback.count.toString()
                feedbackRatingTv.text = it.feedback.rating.toString()
                priceTv.text = it.price.price
                priceTvBtn.text = it.price.price
                priceDiscountTv.text = it.price.priceWithDiscount
                priceDiscountTvBtn.text = it.price.priceWithDiscount
                tvTitleName.text = it.title
                descriptionTv.text = it.description
                itemNumberTv.text = it.info[0]?.title
                itemNumberValueTv.text = it.info[0]?.value
                usageAreaTv.text = it.info[1]?.title
                usageAreaValueTv.text = it.info[1]?.value
                countryCreatedTv.text = it.info[2]?.title ?: ""
                if(countryCreatedTv.text == "") {
                    countryCreatedTv.visibility = View.GONE
                }
                countryCreatedValueTv.text = it.info[2]?.value ?: ""
                if(countryCreatedValueTv.text == "") {
                    countryCreatedValueTv.visibility = View.GONE
                }
                compositionValueTv.text = it.ingredients
            }
            ratingBar.rating = it.feedback.rating.toFloat()
            ratingBar.numStars = 5
            ratingBar.isIndicator
        }
    }

    private fun setupHideBtn() {
        with(binding) {
            btnHideCharacteristics.setOnClickListener {
                if (layoutHided.visibility == View.VISIBLE) {
                    layoutHided.visibility = View.GONE
                    btnHideCharacteristics.text = "Подробнее"
                } else {
                    layoutHided.visibility = View.VISIBLE
                    btnHideCharacteristics.text = "Скрыть"
                }
            }
        }
    }

    private fun setupMoveBack() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, CatalogFragment())
        fragmentTransaction.commit()
    }

}