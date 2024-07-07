package FavoriteFiles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.itis_shop.databinding.FavoriteItemBinding

class FavoriteAdapter(
    private val list:List<Product>,
    private val glide: RequestManager,
): RecyclerView.Adapter<FavoriteFiles.FavoriteHolder>(
)
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : FavoriteHolder {
        return FavoriteHolder(
            binding= FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide= glide,
        )
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.onBind(list[position])
    }
}