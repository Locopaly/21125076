package com.example.order_coffee_app_kotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RewardAdapter(private val rewardList: List<Reward>) : RecyclerView.Adapter<RewardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeType: TextView = view.findViewById(R.id.coffee_type)
        val redeemPoints: TextView = view.findViewById(R.id.points)
        val orderTime: TextView = view.findViewById(R.id.datetime)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rewards_rv_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reward = rewardList[position]

        holder.coffeeType.text = reward.order.cartItem.coffee.coffeeType
        holder.redeemPoints.text = "+ ${reward.redeemPoints} Pts"
        holder.orderTime.text = reward.order.datetime
    }

    override fun getItemCount(): Int {
        return rewardList.size
    }
}