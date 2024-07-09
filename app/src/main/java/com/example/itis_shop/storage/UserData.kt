package com.example.itis_shop.storage

data class UserData(
    var balance: Int = 0,
    val favorites: MutableList<String> = mutableListOf(),
    val shoppingCart: Map<String, Int> = mapOf(),
    val id: String = "",
){}
