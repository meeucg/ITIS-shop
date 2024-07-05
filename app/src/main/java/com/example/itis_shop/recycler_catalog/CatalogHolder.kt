package com.example.itis_shop.recycler_catalog

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.R
import com.example.itis_shop.databinding.ItemCatalogProductBinding
import com.example.itis_shop.storage.Product

class CatalogHolder(
    private val listOfProducts: List<Product>,
    private val binding: ItemCatalogProductBinding,
    private val glide: RequestManager,
    private val onClick: (Int) -> Unit,
) : ViewHolder(binding.root) {

    private val requestOptions = RequestOptions
        .diskCacheStrategyOf(
            DiskCacheStrategy.ALL
        )

    private val context: Context
        get() = itemView.context

    fun onBind(productId: Int) {
        binding.run {
            val product = listOfProducts[productId]
            //cardView.setCardBackgroundColor(Color.parseColor("#FFADB455"))
            tvName.text = product.name
            tvPrice.text = "${product.price}$"

            glide
                .load(product.imageUrl)
                .error(R.drawable.baseline_person_24)
                .placeholder(R.drawable.baseline_person_24)
                .apply(requestOptions)
                .into(ivImage)

            root.setOnClickListener()
            {
                onClick(productId)
            }
        }
    }
}