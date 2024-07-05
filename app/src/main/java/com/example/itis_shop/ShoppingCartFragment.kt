package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itis_shop.databinding.FragmentCatalogBinding
import com.example.itis_shop.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private var binding: FragmentShoppingCartBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}