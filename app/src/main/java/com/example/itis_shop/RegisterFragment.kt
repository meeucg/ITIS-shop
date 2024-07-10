package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itis_shop.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment(R.layout.fragment_register) {
    private var binding: FragmentRegisterBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}