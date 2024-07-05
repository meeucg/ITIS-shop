package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.itis_shop.databinding.FragmentCatalogBinding
import com.example.itis_shop.recycler_catalog.CatalogAdapter
import com.example.itis_shop.storage.Product
import kotlinx.coroutines.awaitAll

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private var binding: FragmentCatalogBinding? = null
    private var adapter: CatalogAdapter? = null

    init {
        storage.addCatalogReadOnCompleteListener({
            adapter?.updateDataset(it)
        }, {

        })
    }

    private fun initAdapter() {
        binding?.run {
            adapter = CatalogAdapter(
                list = storage.catalog,
                glide = Glide.with(this@CatalogFragment),
                onClick = {
//                    findNavController().navigate(resId = R.id.action_birdFragment_to_birdProfileFragment,
//                        BirdProfileFragment.bundle(it))
                    //Snackbar.make(root, BirdRepository.birds[it].name, Snackbar.LENGTH_LONG).show()
                }
            )

            rvCatalog.adapter = adapter

            rvCatalog.layoutManager = StaggeredGridLayoutManager(2, 1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}