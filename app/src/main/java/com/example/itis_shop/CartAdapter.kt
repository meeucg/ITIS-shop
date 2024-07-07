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
    context: Context,
//    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    //карточки товаров    private val managementCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]

        // Вывод информации о товаре
        holder.binding.titleTxt.text = item.name
        holder.binding.feeEachItem.text = "${item.price}₽" // цена за единицу товара
        holder.binding.totalEachItem.text =
            "${Math.round((item.numberInCart * item.price).toDouble())}₽" //итоговая сумма
        holder.binding.numberItemTxt.text = item.numberInCart.toString() //количество товара

        Glide.with(holder.itemView.context)
            .load(item.imageUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.image)

        //Функция для работы +
        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(
                listItemSelected, position,
//                object : ChangeNumberItemsListener {
//                override fun onChanged() {
//                    notifyDataSetChanged()
//                    changeNumberItemsListener?.onChanged()
//                }
//
//            }
            )
        }

        //Функция для работы -
        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(
                listItemSelected,
                position,
//                object : ChangeNumberItemsListener {
//                    override fun onChanged() {
//                        notifyDataSetChanged()
//                        changeNumberItemsListener?.onChanged()
//                    }
//                }
            )
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}