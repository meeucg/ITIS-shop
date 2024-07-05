package com.example.itis_shop.recycler_catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.itis_shop.databinding.ItemCatalogProductBinding
import com.example.itis_shop.storage.Product

class CatalogAdapter(
    private var list: List<Product>,
    private val glide: RequestManager,
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<CatalogHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogHolder = CatalogHolder(
        listOfProducts = list,
        binding = ItemCatalogProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onClick = onClick,
    )

    override fun onBindViewHolder(holder: CatalogHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size

    fun updateDataset(newList: List<Product>) {
        list = newList
        notifyDataSetChanged()
    }
}