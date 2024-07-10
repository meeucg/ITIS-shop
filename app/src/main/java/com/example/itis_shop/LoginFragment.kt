package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.itis_shop.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var binding: FragmentLoginBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding!!.goToRegisterFragmentTv.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}