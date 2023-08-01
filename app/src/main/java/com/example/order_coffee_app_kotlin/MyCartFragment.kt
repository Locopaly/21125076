package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.order_coffee_app_kotlin.databinding.FragmentMyCartBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyCartFragment : Fragment() {

    private lateinit var binding: FragmentMyCartBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyCartBinding.inflate(inflater, container, false)
        recyclerView = binding.cartRecyclerView
        cartAdapter = CartAdapter(Globals.cartItemList)
        recyclerView.adapter = cartAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val cartItemTouchHelperCallback = CartItemTouchHelperCallback(cartAdapter, this)
        val itemTouchHelper = ItemTouchHelper(cartItemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var totalPrice = 0f
        for (i in Globals.cartItemList) totalPrice += i.totalPrice
        binding.totalPrice.text = "$$totalPrice"

        val backClickListener = View.OnClickListener {v ->
            when (v) {
                binding.backButton -> {
                    findNavController().popBackStack()
                }
            }
        }
        binding.backButton.setOnClickListener(backClickListener)

        val checkoutClickListener = View.OnClickListener {v ->
            when (v) {
                binding.checkout -> {
                    for (i in Globals.cartItemList) {
                        Globals.loyaltyPoints += i.coffee.unit

                        val formatter = DateTimeFormatter.ofPattern("dd MMMM | hh:mm a")
                        val currentTime = LocalDateTime.now()
                        val order = Order(i, currentTime.format(formatter))
                        Globals.onGoingOrder.add(order)

                        val reward = Reward(order, i.coffee.unit * 50)
                        Globals.redeemPoints += reward.redeemPoints
                        Globals.rewardHistory.add(reward)
                    }
                    if (Globals.loyaltyPoints >= 8) {
                        val amount: Int = Globals.loyaltyPoints / 8
                        Globals.redeemPoints += amount * 500
                        Globals.loyaltyPoints = 0
                    }
                    Globals.cartItemList.clear()
                    findNavController().navigate(R.id.action_myCartFragment_to_orderSuccessFragment)
                }
            }
        }
        binding.checkout.setOnClickListener(checkoutClickListener)
    }

    fun changeTotalPrice() {
        var totalPrice = 0f
        for (i in Globals.cartItemList) totalPrice += i.totalPrice
        binding.totalPrice.text = "$$totalPrice"
    }
}