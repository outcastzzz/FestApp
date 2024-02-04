package com.register.festapp.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.register.festapp.R
import com.register.festapp.databinding.FragmentBagBinding

class BagFragment : Fragment() {

    private var _binding: FragmentBagBinding? = null
    private val binding: FragmentBagBinding
        get() = _binding ?: throw RuntimeException("FragmentBagBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding.root
    }

}