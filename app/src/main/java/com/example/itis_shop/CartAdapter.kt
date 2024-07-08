package com.example.itis_shop

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<Product>,
    private val context: Context,
    private val changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)

    private val managementCart = Product(context.toString())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]

        // Display product information
        holder.binding.titleTxt.text = item.name
        holder.binding.feeEachItem.text = "${item.price}₽"
        holder.binding.totalEachItem.text = "${(item.numberInCart * item.price).toInt()}₽"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.imageUrl[0])
            .apply(RequestOptions().centerCrop())
            .into(holder.binding.image)

        // Functionality for the + button
        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(
                listItemSelected, position,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.onChanged()
                    }
                }
            )
        }

        // Functionality for the - button
        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(
                listItemSelected, position,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.onChanged()
                    }
                }
            )
        }
    }

    override fun getItemCount(): Int = listItemSelected.size

    interface ChangeNumberItemsListener {
        fun onChanged()
    }
}