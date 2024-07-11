package com.example.itis_shop

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.itis_shop.databinding.DialogInputBinding
import com.example.itis_shop.databinding.FragmentProfileBinding
import com.example.itis_shop.storage.user_id

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null
    private var balance: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        updateBalanceDisplay()

        binding!!.addition.setOnClickListener {
            showInputDialog()
        }
        binding!!.buttonExit.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

    private fun showInputDialog() {
        val dialogBinding = DialogInputBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.btnConfirm.setOnClickListener {
            val input = dialogBinding.etInput.text.toString()
            if (input.isNotEmpty()) {
                try {
                    val amount = input.toInt()
                    storage.updateBalance(user_id, storage.userData.balance + amount,
                        onEnd = {
                            updateBalanceDisplay()
                        })
                    dialog.dismiss()
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Неверный формат числа", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Введите сумму", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    @SuppressLint("DefaultLocale")
    private fun updateBalanceDisplay() {
        binding!!.balance.text = String.format("Balance: %d", storage.userData.balance)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

