package recycler_favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.itis_shop.databinding.ItemFavoriteBinding
import com.example.itis_shop.storage.Product

class FavoriteAdapter(
    private val list: List<Product>,
    private val glide: RequestManager,
    private val onClick: (String) -> Unit
): RecyclerView.Adapter<FavoriteHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : FavoriteHolder = FavoriteHolder(
            binding= ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide= glide,
            onClick = onClick
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.onBind(list[position])
    }
}