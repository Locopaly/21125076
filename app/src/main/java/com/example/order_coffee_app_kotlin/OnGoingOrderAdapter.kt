package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnGoingOrderAdapter(private val onGoingOrderList: List<Order>) : RecyclerView.Adapter<OnGoingOrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeType: TextView = view.findViewById(R.id.coffee_type)
        val totalPrice: TextView = view.findViewById(R.id.total_price)
        val address: TextView = view.findViewById(R.id.address)
        val orderTime: TextView = view.findViewById(R.id.datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ongoing_order_rv_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = onGoingOrderList[position]

        holder.coffeeType.text = order.cartItem.coffee.coffeeType
        holder.totalPrice.text = "$${order.cartItem.totalPrice}"
        holder.address.text = Globals.user.address
        holder.orderTime.text = order.datetime
    }

    override fun getItemCount(): Int {
        return onGoingOrderList.size
    }
}