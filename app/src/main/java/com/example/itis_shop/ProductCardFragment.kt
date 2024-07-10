package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.databinding.FragmentProductCardBinding
import com.example.itis_shop.storage.user_id

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {

    private var binding: FragmentProductCardBinding? = null
    private val requestOptions = RequestOptions
        .diskCacheStrategyOf(
            DiskCacheStrategy.ALL
        )
    private var state: Boolean = true
    private var iconState: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductCardBinding.bind(view)

        //gets a Firestore Id (catalog/id)
        val id: String = arguments?.getString(ARG_ID) ?: ""
        val backId: Int = arguments?.getInt(ARG_BACK_ID) ?: R.id.catalogFragment
        val glide = Glide.with(this@ProductCardFragment)

        state = !storage.userData.shoppingCart.keys.contains(id)

        binding?.run {
            if(!state){
                buyBtn.text = "Go to shopping cart"
            }

            iconState = storage.userData
                .favorites.contains(id)

            when(iconState)
            {
                true -> {
                    btnLike.setBackgroundResource(R.drawable.heart_icon_filled_white)
                }
                else -> {
                    btnLike.setBackgroundResource(R.drawable.heart_icon_outline_white)
                }

            }

            storage.readCatalogById(id, onEnd = {
                glide
                    .load(it.imageUrl)
                    .error(R.drawable.baseline_person_24)
                    .placeholder(R.drawable.baseline_person_24)
                    .apply(requestOptions)
                    .into(ivImage)

                tvName.text = it.name
                tvDescription.text = it.description

                btnLike.setOnClickListener{
                    when(iconState)
                    {
                        true -> {
                            btnLike.setBackgroundResource(R.drawable.heart_icon_outline_white)
                            storage.removeFromFavorite(productId = id, userId = user_id)
                            iconState = !iconState
                        }
                        else -> {
                            btnLike.setBackgroundResource(R.drawable.heart_icon_filled_white)
                            storage.addToFavorite(productId = id, userId = user_id)
                            iconState = !iconState
                        }

                    }
                }

                backBtn.setOnClickListener{
                    findNavController().navigateUp()
                }

                buyBtn.setOnClickListener{
                    when(state){
                        true -> {storage.addToShoppingCart(userId = user_id,
                            productId = id,
                            count = 1)
                        buyBtn.text = "Go to shopping cart"
                        state = !state}
                        false -> findNavController()
                            .navigate(R.id.action_productCardFragment_to_shoppingCartFragment,
                                null,
                                navOptions = NavOptions.Builder()
                                    .setPopUpTo(backId, true).build())
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object{
        val ARG_ID = "ARG_ID"
        val ARG_BACK_ID = "ARG_BACK_ID"

        fun bundle(id: String, backId: Int): Bundle = Bundle().apply{
            putString(ARG_ID, id)
            putInt(ARG_BACK_ID, backId)
        }
    }
}