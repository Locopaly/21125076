package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.order_coffee_app_kotlin.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        Globals.setLoyaltyCardView(binding.root)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameDisplay.text = Globals.user.name
        binding.loyaltyPoints.text = "${Globals.loyaltyPoints} / 8"

        val profileClickListener = View.OnClickListener {v ->
            when (v) {
                binding.profileButton -> {
                    findNavController().navigate(R.id.action_homeScreenFragment_to_profileFragment)
                }
            }
        }
        binding.profileButton.setOnClickListener(profileClickListener)

        val cartClickListener = View.OnClickListener {v ->
            when (v) {
                binding.cartButton -> {
                    findNavController().navigate(R.id.action_homeScreenFragment_to_myCartFragment)
                }
            }
        }
        binding.cartButton.setOnClickListener(cartClickListener)

        val detailsClickListener = View.OnClickListener {v ->
            when (v) {
                binding.americano -> {
                    val bundle = Bundle()
                    bundle.putInt("CoffeeClass", 1)
                    val detailsFragment = DetailsFragment()
                    detailsFragment.arguments = bundle
                    findNavController().navigate(R.id.action_homeScreenFragment_to_detailsFragment, bundle)
                }
                binding.cappuccino -> {
                    val bundle = Bundle()
                    bundle.putInt("CoffeeClass", 2)
                    val detailsFragment = DetailsFragment()
                    detailsFragment.arguments = bundle
                    findNavController().navigate(R.id.action_homeScreenFragment_to_detailsFragment, bundle)
                }
                binding.mocha -> {
                    val bundle = Bundle()
                    bundle.putInt("CoffeeClass", 3)
                    val detailsFragment = DetailsFragment()
                    detailsFragment.arguments = bundle
                    findNavController().navigate(R.id.action_homeScreenFragment_to_detailsFragment, bundle)
                }
                binding.flatWhite -> {
                    val bundle = Bundle()
                    bundle.putInt("CoffeeClass", 4)
                    val detailsFragment = DetailsFragment()
                    detailsFragment.arguments = bundle
                    findNavController().navigate(R.id.action_homeScreenFragment_to_detailsFragment, bundle)
                }
            }
        }
        binding.americano.setOnClickListener(detailsClickListener)
        binding.cappuccino.setOnClickListener(detailsClickListener)
        binding.mocha.setOnClickListener(detailsClickListener)
        binding.flatWhite.setOnClickListener(detailsClickListener)
    }
}