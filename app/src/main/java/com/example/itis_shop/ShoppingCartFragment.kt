package com.example.itis_shop

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.itis_shop.databinding.FragmentShoppingCartBinding
import com.example.itis_shop.recycler_shopping_cart.ShoppingCartAdapter
import com.example.itis_shop.storage.user_id
import com.example.itis_shop.tools.BottomOffsetDecorator
import com.google.android.material.snackbar.Snackbar

class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private var binding: FragmentShoppingCartBinding? = null
    private var adapter: ShoppingCartAdapter? = null

    @OptIn(ExperimentalStdlibApi::class)
    private fun initAdapter() {
        binding?.run {
            adapter = ShoppingCartAdapter(
                parentBinding = binding!!,
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

            buyAllBtn.setOnClickListener{
                if(storage.userData.balance < storage.shoppingCartTotalPrice){
                    Snackbar.make(
                        root, "Not enough money", Snackbar.LENGTH_LONG).show()
                } else {
                    for(productId in storage.userData.shoppingCart.keys){
                        storage.removeFromShoppingCart(user_id, productId,
                            onEnd = {
                                adapter?.updateDataset(storage.shoppingCart.keys.toList())
                                tvTotalPrice.text = "Total: 0$"
                            })
                        storage.updateBalance(user_id, storage.userData.balance
                                - storage.shoppingCartTotalPrice)
                        storage.shoppingCartTotalPrice = 0
                    }
                }
            }
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