package com.example.order_coffee_app_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.order_coffee_app_kotlin.databinding.FragmentRedeemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RedeemFragment : Fragment() {

    private lateinit var binding: FragmentRedeemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRedeemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backClickListener = View.OnClickListener {v ->
            when (v) {
                binding.backButton -> {
                    findNavController().popBackStack()
                }
            }
        }
        binding.backButton.setOnClickListener(backClickListener)

        setOnClickListenerForRedeemButtons()
    }

    private fun setOnClickListenerForRedeemButtons() {
        val americanoRedeemClickListener = View.OnClickListener {v ->
            when (v) {
                binding.americanoRedeemButton -> {
                    if (Globals.redeemPoints >= 1340) {
                        val coffeeType = "Americano"
                        redeemOrder(coffeeType)
                    }
                }
            }
        }
        binding.americanoRedeemButton.setOnClickListener(americanoRedeemClickListener)

        val cappuccinoRedeemClickListener = View.OnClickListener {v ->
            when (v) {
                binding.cappuccinoRedeemButton -> {
                    if (Globals.redeemPoints >= 1340) {
                        val coffeeType = "Cappuccino"
                        redeemOrder(coffeeType)
                    }
                }
            }
        }
        binding.cappuccinoRedeemButton.setOnClickListener(cappuccinoRedeemClickListener)

        val flatWhiteRedeemClickListener = View.OnClickListener {v ->
            when (v) {
                binding.flatWhiteRedeemButton -> {
                    if (Globals.redeemPoints >= 1340) {
                        val coffeeType = "Flat White"
                        redeemOrder(coffeeType)
                    }
                }
            }
        }
        binding.flatWhiteRedeemButton.setOnClickListener(flatWhiteRedeemClickListener)
    }

    private fun redeemOrder(coffeeType: String) {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM | hh:mm a")
        val currentTime = LocalDateTime.now()
        val coffee = CoffeeClass(coffeeType, 1, true, 0f, Shot.Single, Ice.Medium, Size.Medium)
        val cartItem = CartItem(coffee, coffee.price)
        val order = Order(cartItem, currentTime.format(formatter))
        val reward = Reward(order, 0)
        Globals.onGoingOrder.add(order)
        Globals.rewardHistory.add(reward)
        Globals.redeemPoints -= 1340
    }
}