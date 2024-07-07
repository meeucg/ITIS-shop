package FavoriteFiles

import android.app.AlertDialog
import android.app.Dialog
import android.widget.Button
import androidx.appcompat.widget.AlertDialogLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.itis_shop.FavoriteFragment
import com.example.itis_shop.MainActivity
import com.example.itis_shop.databinding.FavoriteItemBinding
import com.google.android.material.snackbar.Snackbar

class FavoriteHolder(
    private val binding:FavoriteItemBinding,
    private val glide: RequestManager,
):ViewHolder(binding.root) {



    fun onBind(favProduct: Product) {
        binding.run{
            itemPrice.text= favProduct.price.toString()
            itemName.text=favProduct.name

            glide
                .load(favProduct.url)
                .into(itemImage)
            root.setOnClickListener {


            }


            }
        }
    }





}
