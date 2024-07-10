package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.itis_shop.databinding.FragmentShoppingCartBinding
import com.example.itis_shop.recycler_shopping_cart.ShoppingCartAdapter
import com.example.itis_shop.tools.BottomOffsetDecorator

class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private var binding: FragmentShoppingCartBinding? = null
    private var adapter: ShoppingCartAdapter? = null


    private fun initAdapter() {
        binding?.run {
            adapter = ShoppingCartAdapter(
                list = storage.shoppingCart.keys.toList(),
                glide = Glide.with(this@ShoppingCartFragment),
                onClick = {
                    findNavController().navigate(resId = R.id.action_shoppingCartFragment_to_productCardFragment,
                        ProductCardFragment.bundle(it, R.id.shoppingCartFragment))
                }
            )

            rvShoppingCart.adapter = adapter
            rvShoppingCart.layoutManager = LinearLayoutManager(requireContext())
            rvShoppingCart.addItemDecoration(BottomOffsetDecorator(300))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartBinding.bind(view)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}