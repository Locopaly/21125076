package com.example.order_coffee_app_kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeScreenFragment -> {
                    navController.navigate(R.id.homeScreenFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.rewardsFragment -> {
                    navController.navigate(R.id.rewardsFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.orderOngoingFragment -> {
                    navController.navigate(R.id.orderOngoingFragment)
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeScreenFragment ||
                destination.id == R.id.rewardsFragment ||
                destination.id == R.id.orderOngoingFragment ||
                destination.id == R.id.orderHistoryFragment) bottomNavigationView.visibility = View.VISIBLE
            else bottomNavigationView.visibility = View.GONE
        }
    }
}