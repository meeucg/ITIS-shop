package com.example.itis_shop.storage

import android.util.Log

// @OptIn(ExperimentalStdlibApi::class)
data class Product(
    val name: String = "Name",
    val price: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
) {
    // fun getId(): String = this.hashCode().toHexString()
}