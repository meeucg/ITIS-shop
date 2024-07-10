package com.example.itis_shop.recycler_shopping_cart

import android.content.Context
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.transition.Visibility
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.R
import com.example.itis_shop.databinding.ItemShoppingCartProductBinding
import com.example.itis_shop.storage
import com.example.itis_shop.storage.Product
import com.example.itis_shop.storage.user_id

class ShoppingCartHolder(
    private val listOfProducts: List<Product>,
    private val binding: ItemShoppingCartProductBinding,
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

            tvProductCount.text = storage.userData.shoppingCart[product.id].toString()

            when(iconState)
            {
                true -> {
                    btnLike.setBackgroundResource(R.drawable.heart_icon_filled_black)
                }
                else -> {
                    btnLike.setBackgroundResource(R.drawable.heart_icon_outline_black)
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
                        btnLike.setBackgroundResource(R.drawable.heart_icon_outline_black)
                        storage.removeFromFavorite(productId = product.id, userId = user_id)
                        iconState = !iconState
                    }
                    else -> {
                        btnLike.setBackgroundResource(R.drawable.heart_icon_filled_black)
                        storage.addToFavorite(productId = product.id, userId = user_id)
                        iconState = !iconState
                    }

                }

            }

            btnRemove.setOnClickListener{
                storage.removeFromShoppingCart(user_id, product.id)
                btnRemove.isEnabled = false
                btnMinus.isEnabled = false
                btnPlus.isEnabled = false
                btnLike.isEnabled = false
                block.visibility = View.VISIBLE
            }

            btnPlus.setOnClickListener{
                storage.incrementCountByCountShoppingCart(user_id, product.id, 1,
                    onEnd = {
                        tvProductCount.text = storage.userData.shoppingCart[product.id].toString()
                    })
            }

            btnMinus.setOnClickListener{
                storage.incrementCountByCountShoppingCart(user_id, product.id, -1,
                    onEnd = {
                        tvProductCount.text = storage.userData.shoppingCart[product.id].toString()
                    })
            }
        }
    }
}