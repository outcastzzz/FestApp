package com.register.festapp.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.register.festapp.R
import com.register.festapp.databinding.FragmentDiscountBinding
import com.register.festapp.databinding.FragmentProfileBinding

class DiscountFragment : Fragment() {

    private var _binding: FragmentDiscountBinding? = null
    private val binding: FragmentDiscountBinding
        get() = _binding ?: throw RuntimeException("FragmentDiscountBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscountBinding.inflate(inflater, container, false)
        return binding.root
    }

}