package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itis_shop.databinding.FragmentCatalogBinding
import com.example.itis_shop.databinding.FragmentProfileBinding
import com.example.itis_shop.storage.Product

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding?.run {
            tvTitle1.setOnClickListener{
                storage.addToCatalog(Product(
                    name = "Nike Monarch",
                    price=400,
                    imageUrl = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/a487f641-a6d6-4acc-9b49-79c70ddd6ee8/air-monarch-iv-amp-mens-workout-shoes-dChTJ7.png"))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}