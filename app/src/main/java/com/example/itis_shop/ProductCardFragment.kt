package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.databinding.FragmentProductCardBinding

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {

    private var binding: FragmentProductCardBinding? = null
    private val requestOptions = RequestOptions
        .diskCacheStrategyOf(
            DiskCacheStrategy.ALL
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductCardBinding.bind(view)

        //gets a Firestore Id (catalog/id)
        val id: String = arguments?.getString(ARG_ID) ?: ""
        val glide = Glide.with(this@ProductCardFragment)

        binding?.run {
            storage.readCatalogById(id, onEnd = {
                glide
                    .load(it.imageUrl)
                    .error(R.drawable.baseline_person_24)
                    .placeholder(R.drawable.baseline_person_24)
                    .apply(requestOptions)
                    .into(ivImage)

                tvName.text = it.name
                tvDescription.text = it.description


                backBtn.setOnClickListener{
                    findNavController().navigateUp()
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

        fun bundle(id: String): Bundle = Bundle().apply{
            putString(ARG_ID, id)
        }
    }
}