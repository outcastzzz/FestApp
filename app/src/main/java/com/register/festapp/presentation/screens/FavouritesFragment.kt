package com.register.festapp.presentation.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.register.festapp.R
import com.register.festapp.databinding.FragmentFavouritesBinding
import com.register.festapp.databinding.FragmentProfileBinding
import com.register.festapp.presentation.MainViewModel
import com.register.festapp.presentation.ShoppingApp
import com.register.festapp.presentation.ViewModelFactory
import com.register.festapp.presentation.adapter.ShopAdapter
import javax.inject.Inject

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding: FragmentFavouritesBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouritesBinding == null")

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
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        val adapter = ShopAdapter(viewModel)
        binding.rvFavouriteItems.adapter = adapter
        binding.rvFavouriteItems.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.moveBackIv.setOnClickListener {
            moveBackFragment()
        }
    }

    private fun moveBackFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, ProfileFragment())
        fragmentTransaction.commit()
    }

}