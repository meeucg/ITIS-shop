package com.example.itis_shop.storage

data class UserData(
    var balance: Int = 0,
    val favorites: MutableList<String> = mutableListOf(),
    val shoppingCart: MutableMap<String, Int> = mutableMapOf(),
    val id: String = "",
){}
