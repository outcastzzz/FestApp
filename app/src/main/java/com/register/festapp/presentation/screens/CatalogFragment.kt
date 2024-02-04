package com.register.festapp.presentation.screens

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.register.festapp.R
import com.register.festapp.databinding.FragmentCatalogBinding
import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.presentation.MainViewModel
import com.register.festapp.presentation.ShoppingApp
import com.register.festapp.presentation.ViewModelFactory
import com.register.festapp.presentation.adapter.ImageSliderAdapter
import com.register.festapp.presentation.adapter.ShopAdapter
import javax.inject.Inject

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding: FragmentCatalogBinding
        get() = _binding ?: throw RuntimeException("FragmentCatalogBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    private val component by lazy {
        (requireActivity().application as ShoppingApp).component
    }

    private val selectedViews = mutableSetOf<TextView>()
    private val items = listOf("Смотреть все", "Лицо", "Тело", "Загар", "Маска")
    private var defaultList: MutableList<ShopItem> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        observeViewModel()
        setupFilterPanel()
    }

    private fun observeViewModel() {
        val adapter = ShopAdapter(viewModel)
        binding.rvShopItems.adapter = adapter
        binding.rvShopItems.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.itemList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            defaultList = it.toMutableList()
            Log.d("adapterTag", "list $defaultList}")
        }
        adapter.setOnItemClickListener(object: ShopAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.getShopItem(position)
                launchShopItemFragment()
            }
        })
    }

    private fun setupFilterPanel() {
        val container = binding.container
        val inflater = LayoutInflater.from(requireContext())

        items.forEachIndexed { index, item ->
            val textView = inflater.inflate(R.layout.text_view_item, container, false) as TextView
            textView.text = item
            textView.setOnClickListener {
                handleClick(textView)
            }
            container.addView(textView)
            if (index == 0) {
                setSelected(textView, true)
            }
        }
    }

    private fun setSelected(textView: TextView, isSelected: Boolean) {
        if (isSelected) {
            textView.setBackgroundResource(R.drawable.ic_background_filter_items)
            textView.setTextColor(Color.WHITE)
            val crossDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_item_clear)?.apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, crossDrawable, null)
            selectedViews.add(textView)
            val adapter = binding.rvShopItems.adapter as? ShopAdapter
            adapter?.let { rvAdapter ->
                val filteredList = when (textView.text.toString()) {
                    "Лицо" -> defaultList.filter { it.tags.contains("face") }
                    "Тело" -> defaultList.filter { it.tags.contains("body") }
                    "Загар" -> defaultList.filter { it.tags.contains("suntan") }
                    "Маска" -> defaultList.filter { it.tags.contains("mask") }
                    else -> defaultList
                }
                rvAdapter.submitList(filteredList)
            }
            Log.d("adapterTag", "done2")
        } else {
            textView.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_item_empty_background)
            textView.setTextColor(Color.BLACK)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            selectedViews.remove(textView)
        }
    }

    private fun handleClick(textView: TextView) {
        if (textView in selectedViews) {
            setSelected(textView, false)
        } else {
            selectedViews.forEach { setSelected(it, false) }
            setSelected(textView, true)
        }
    }

    private fun launchShopItemFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, ShopItemInfoFragment())
        fragmentTransaction.commit()
    }

}