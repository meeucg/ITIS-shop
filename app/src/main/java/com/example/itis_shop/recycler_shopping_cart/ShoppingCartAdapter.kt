package com.example.itis_shop.recycler_shopping_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.itis_shop.databinding.FragmentShoppingCartBinding
import com.example.itis_shop.databinding.ItemShoppingCartProductBinding
import com.example.itis_shop.storage.Product

class ShoppingCartAdapter(
    private val parentBinding: FragmentShoppingCartBinding,
    private var list: List<Product>,
    private val glide: RequestManager,
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<ShoppingCartHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingCartHolder = ShoppingCartHolder(
        parentBinding = parentBinding,
        listOfProducts = list,
        binding = ItemShoppingCartProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onClick = onClick,
    )

    override fun onBindViewHolder(holder: ShoppingCartHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size

    fun updateDataset(newList: List<Product>) {
        list = newList
        notifyDataSetChanged()
    }
}