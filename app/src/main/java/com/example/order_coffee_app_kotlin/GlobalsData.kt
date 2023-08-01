package com.example.order_coffee_app_kotlin

import android.view.View
import android.widget.ImageView

object Globals {
    var onGoingOrder = mutableListOf<Order>()
    var historyOrder = mutableListOf<Order>()
    var rewardHistory = mutableListOf<Reward>()
    var cartItemList = mutableListOf<CartItem>()
    var loyaltyPoints: Int = 0
    var user: User = User("Thien Duc", "htduc21@apcs.fitus.edu.vn", "0123456789", "227 Nguyen Van Cu Street, District 5, Ho Chi Minh city")
    var redeemPoints: Int = 0

    fun setLoyaltyCardView(view: View) {
        for (i in 1..8) {
            val name = "loyalty_cup_$i"
            val id = view.resources.getIdentifier(name, "id", view.context.packageName)
            val imageView = view.findViewById<ImageView>(id)

            if (i <= loyaltyPoints) {
                imageView.setImageResource(R.drawable.loyalty_cup)
            } else {
                imageView.setImageResource(R.drawable.not_loyalty_cup)
            }
        }
    }
}