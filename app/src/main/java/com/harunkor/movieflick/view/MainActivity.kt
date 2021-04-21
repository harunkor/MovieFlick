package com.harunkor.movieflick.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.harunkor.movieflick.R
import com.harunkor.movieflick.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Data binding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpBottomNavigationView()





    }



    // Setup bottom navigation view
    private fun setUpBottomNavigationView() {

        // Nav host fragment
        val navHost: NavHostFragment = this?.supportFragmentManager
                ?.findFragmentById(binding.navHostFragment.id) as NavHostFragment?
                ?: return
        // Setup bottom navigationview
        binding?.idBottomNavigate?.setupWithNavController(navHost.navController)
    }
    // end Setup bottom navigation view





}