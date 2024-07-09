package com.example.itis_shop

import android.content.Context

class ManagementCart(private val context: Context) {
    fun plusItem(
        listItemSelected: ArrayList<Product>,
        position: Int,
        changeNumberItemsListener: CartAdapter.ChangeNumberItemsListener
    ) {

        listItemSelected[position].numberInCart++
        changeNumberItemsListener.onChanged()
    }

    fun minusItem(
        listItemSelected: ArrayList<Product>,
        position: Int,
        changeNumberItemsListener: CartAdapter.ChangeNumberItemsListener
    ) {

        if (listItemSelected[position].numberInCart > 1) {
            listItemSelected[position].numberInCart--
            changeNumberItemsListener.onChanged()
        }
    }
}