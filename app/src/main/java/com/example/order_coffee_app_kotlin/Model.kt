package com.example.order_coffee_app_kotlin

data class CartItem(
    var coffee: CoffeeClass,
    var totalPrice: Float,
)

data class CoffeeClass(
    var coffeeType: String,
    var unit: Int,
    var isTakeAway: Boolean,
    var price: Float,
    var shot: Shot,
    var ice: Ice,
    var size: Size
)

enum class Shot {
    Single,
    Double
}

enum class Size {
    Small,
    Medium,
    Big
}

enum class Ice {
    Small,
    Medium,
    Big
}

data class User (
    var name: String,
    var email: String,
    var phoneNumber: String,
    var address: String
)

data class Order (
    var cartItem: CartItem,
    var datetime: String
)

data class Reward (
    var order: Order,
    var redeemPoints: Int
)