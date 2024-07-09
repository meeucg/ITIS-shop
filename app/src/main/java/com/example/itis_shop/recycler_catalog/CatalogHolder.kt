package com.example.itis_shop.recycler_catalog

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.R
import com.example.itis_shop.databinding.ItemCatalogProductBinding
import com.example.itis_shop.storage
import com.example.itis_shop.storage.Product
import com.example.itis_shop.storage.user_id

class CatalogHolder(
    private val listOfProducts: List<Product>,
    private val binding: ItemCatalogProductBinding,
    private val glide: RequestManager,
    private val onClick: (String) -> Unit,
) : ViewHolder(binding.root) {

    private val requestOptions = RequestOptions
        .diskCacheStrategyOf(
            DiskCacheStrategy.ALL
        )

    private val context: Context
        get() = itemView.context

    private var iconState: Boolean = false

    fun onBind(productId: Int) {
        binding.run {
            val product = listOfProducts[productId]
            iconState = storage.userData
                .favorites.contains(product.id)

            when(iconState)
            {
                true -> {
                    btnLike.setBackgroundResource(R.drawable.heart_icon_filled)
                }
                else -> {
                    btnLike.setBackgroundResource(R.drawable.heart_icon_outline)
                }

            }

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
                onClick(product.id)
            }

            btnLike.setOnClickListener{
                when(iconState)
                {
                    true -> {
                        btnLike.setBackgroundResource(R.drawable.heart_icon_outline)
                        storage.removeFromFavorite(productId = product.id, userId = user_id)
                        iconState = !iconState
                    }
                    else -> {
                        btnLike.setBackgroundResource(R.drawable.heart_icon_filled)
                        storage.addToFavorite(productId = product.id, userId = user_id)
                        iconState = !iconState
                    }

                }

            }
        }
    }
}