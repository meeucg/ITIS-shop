package com.example.itis_shop.storage

@OptIn(ExperimentalStdlibApi::class)
data class Product(
    val name: String = "Name",
    val price: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
) {
    val id = this.hashCode().toHexString()
}