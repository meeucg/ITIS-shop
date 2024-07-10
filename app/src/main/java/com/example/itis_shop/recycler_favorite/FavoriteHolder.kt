package recycler_favorite

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.example.itis_shop.R
import com.example.itis_shop.databinding.ItemFavoriteBinding
import com.example.itis_shop.storage
import com.example.itis_shop.storage.Product
import com.example.itis_shop.storage.user_id

class FavoriteHolder(
    private val binding:ItemFavoriteBinding,
    private val glide: RequestManager,
    private val onClick: (String) -> Unit
):ViewHolder(binding.root)
{
    private var iconState = true

    fun onBind(favProduct: Product) {
        binding.run{
            tvPrice.text= "${favProduct.price}$"
            tvName.text=favProduct.name

            btnLike.setOnClickListener{
                when(iconState)
                {
                    true -> {
                        btnLike.setBackgroundResource(R.drawable.heart_icon_outline_black)
                        storage.removeFromFavorite(productId = favProduct.id, userId = user_id)
                        iconState = !iconState
                    }
                    else -> {
                        btnLike.setBackgroundResource(R.drawable.heart_icon_filled_black)
                        storage.addToFavorite(productId = favProduct.id, userId = user_id)
                        iconState = !iconState
                    }
                }
            }

            glide
                .load(favProduct.imageUrl)
                .into(ivImage)

            root.setOnClickListener {
                onClick(favProduct.id)
            }
        }
    }
}