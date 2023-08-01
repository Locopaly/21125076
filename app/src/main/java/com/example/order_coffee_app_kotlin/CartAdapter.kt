package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItemList: List<CartItem>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeType: TextView = view.findViewById(R.id.coffee_type)
        val coffeeTotalPrice: TextView = view.findViewById(R.id.total_price)
        val coffeeAmount: TextView = view.findViewById(R.id.unit)
        val coffeeDetails: TextView = view.findViewById(R.id.details)
        val coffeeImage: ImageView = view.findViewById(R.id.coffee_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_rv_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartItemList[position]
        when (cartItem.coffee.coffeeType) {
            "Americano" -> {
                holder.coffeeType.text = "Americano"
                holder.coffeeImage.setImageResource(R.drawable.americano)
            }
            "Cappuccino" -> {
                holder.coffeeType.text = "Cappuccino"
                holder.coffeeImage.setImageResource(R.drawable.cappuccino)
            }
            "Mocha" -> {
                holder.coffeeType.text = "Mocha"
                holder.coffeeImage.setImageResource(R.drawable.mocha)
            }
            "Flat White" -> {
                holder.coffeeType.text = "Flat White"
                holder.coffeeImage.setImageResource(R.drawable.flat_white)
            }
        }
        holder.coffeeTotalPrice.text = "$${cartItem.totalPrice}"
        holder.coffeeAmount.text = "x ${cartItem.coffee.unit}"
        holder.coffeeDetails.text = setDetails(
            cartItem.coffee.shot,
            cartItem.coffee.isTakeAway,
            cartItem.coffee.size,
            cartItem.coffee.ice
        )
    }

    private fun setDetails(shot: Shot, isTakeAway: Boolean, size: Size, ice: Ice): String {
        var details = ""
        if (shot == Shot.Single) {
            details += "single"
        } else {
            details += "double"
        }

        if (isTakeAway) {
            details += " | take away"
        } else {
            details += " | for here"
        }

        when (size) {
            Size.Small -> {
                details += " | small"
            }
            Size.Medium -> {
                details += " | medium"
            }
            Size.Big -> {
                details += " | large"
            }
        }

        when (ice) {
            Ice.Small -> {
                details += " | no ice"
            }
            Ice.Medium -> {
                details += " | normal ice"
            }
            Ice.Big -> {
                details += " | full ice"
            }
        }

        return details
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }
}