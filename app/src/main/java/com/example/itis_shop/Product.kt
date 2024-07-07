package com.example.itis_shop

@OptIn(ExperimentalStdlibApi::class)
data class Product(
    val name: String = "Name",
    val price: Int = 0,
    val description: String = "",
    val imageUrl: String = "",

    var numberInCart: Int = 0
) {
    val id = this.hashCode().toHexString()
}

