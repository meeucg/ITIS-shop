package com.example.itis_shop

import recycler_favorite.FavoriteAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.itis_shop.databinding.FragmentFavoriteBinding
import com.example.itis_shop.tools.GridBottomOffsetDecorator

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
        adapter= FavoriteAdapter(list= storage.favorites,
            glide = Glide.with(this),
            onClick = {
                findNavController().navigate(resId = R.id.action_favoriteFragment_to_productCardFragment,
                    ProductCardFragment.bundle(it))
            })
        binding?.run{
            rvFavorite.adapter=adapter
            rvFavorite.layoutManager=StaggeredGridLayoutManager(2, 1)
            rvFavorite.addItemDecoration(GridBottomOffsetDecorator(300))
        }
    }
}