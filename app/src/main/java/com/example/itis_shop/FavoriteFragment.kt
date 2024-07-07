package com.example.itis_shop

import FavoriteFiles.FavoriteAdapter
import FavoriteFiles.SneakersRepository
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.itis_shop.databinding.FragmentCatalogBinding
import com.example.itis_shop.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var binding: FragmentFavoriteBinding? = null
    private var adapter: FavoriteAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun initAdapter(){
        adapter= FavoriteAdapter(list= SneakersRepository.sneakers, glide = Glide.with(this))
        binding?.run{
            rvFavorite.adapter=adapter
            rvFavorite.layoutManager=GridLayoutManager(requireContext(), 2)
        }
    }


}