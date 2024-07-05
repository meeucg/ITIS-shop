package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itis_shop.databinding.FragmentCatalogBinding
import com.example.itis_shop.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}