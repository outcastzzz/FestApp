package com.register.festapp.presentation

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.register.festapp.R
import com.register.festapp.databinding.ActivityHostBinding
import com.register.festapp.presentation.screens.BagFragment
import com.register.festapp.presentation.screens.CatalogFragment
import com.register.festapp.presentation.screens.DiscountFragment
import com.register.festapp.presentation.screens.HomeFragment
import com.register.festapp.presentation.screens.ProfileFragment

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()

    }

    private fun setupNavigation() {
        val bottomView = binding.navView
        bottomView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.navigation_cart -> {
                    replaceFragment(BagFragment())
                    true
                }
                R.id.navigation_offers -> {
                    replaceFragment(DiscountFragment())
                    true
                }
                R.id.navigation_catalog -> {
                    replaceFragment(CatalogFragment())
                    true
                }
                else -> false
            }

        }
        bottomView.selectedItemId = R.id.navigation_catalog
        replaceFragment(CatalogFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainerView.id, fragment)
        fragmentTransaction.commit()
    }

}