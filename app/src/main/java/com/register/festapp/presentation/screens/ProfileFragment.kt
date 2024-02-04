package com.register.festapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.register.festapp.R
import com.register.festapp.databinding.FragmentProfileBinding
import com.register.festapp.databinding.FragmentShopItemInfoBinding
import com.register.festapp.presentation.MainViewModel
import com.register.festapp.presentation.ShoppingApp
import com.register.festapp.presentation.ViewModelFactory
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        viewModel.getUserData()
        observeViewModel()
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            binding.tvFavouritesCount.text = it.size.toString()
        }
        binding.layoutFavourites.setOnClickListener {
            launchFavouritesFragment()
        }
        binding.btnLogout.setOnClickListener {
            viewModel.clearDatabase()
            requireActivity().finish()
        }
    }

    private fun observeViewModel() {
        viewModel.userData.observe(viewLifecycleOwner) {
            binding.userNameDataTv.text = "${it.name} ${it.surname}"
            binding.userPhoneTv.text = it.phoneNumber
        }
    }

    private fun launchFavouritesFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, FavouritesFragment())
        fragmentTransaction.commit()
    }


}