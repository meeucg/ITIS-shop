package com.example.itis_shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itis_shop.databinding.FragmentShoppingCartBinding
import kotlin.Int.Companion as Int1

class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private var binding: FragmentShoppingCartBinding? = null

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: ArrayList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartBinding.bind(view)

        // RecyclerView
        cartAdapter = CartAdapter() // передать список товаров в корзине
        binding!!.viewCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        binding!!.button.setOnClickListener {
        }

        binding!!.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        calculateCart()

    }

    private fun calculateCart() {
        val percentDiscount = 0.1
        val delivery = 10.0
        val discount = Math.round((Product.price * percentDiscount) * 100) / 100.0
        val total = Math.round((Product.price - discount + delivery) * 100) / 100.0
        val itemTotal = Product.price

        with(binding!!) {
            totalFeeTxt.text = "$itemTotal"
            deliveryTxt.text = "$delivery"
            discountTxt.text = "$discount"
            totalTxt.text = "$total"
        }
        with(binding!!) {
            emptyTxt.visibility = if (cartItems.isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility = if (cartItems.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}