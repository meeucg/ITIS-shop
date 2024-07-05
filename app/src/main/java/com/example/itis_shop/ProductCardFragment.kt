package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itis_shop.databinding.FragmentCatalogBinding
import com.example.itis_shop.databinding.FragmentFavoriteBinding
import com.example.itis_shop.databinding.FragmentProductCardBinding

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {

    private var binding: FragmentProductCardBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductCardBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}