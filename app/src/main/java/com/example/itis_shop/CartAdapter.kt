package com.example.itis_shop

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itis_shop.databinding.ViewholderCartBinding

class CartAdapter(

    private var listItemSelected: ArrayList<Product> =  arrayListOf(
Product("Converse Chuck 70", 199, "Кеды Converse All Star Chuck ’70 - это классика с современными деталями. Они отличаются более высоким резиновым покрытием на подошве, мягкой стелькой, обеспечивающей длительный комфорт, и более прочным резиновым мыском.", "https://static.street-beat.ru/upload/resize_cache/iblock/2a6/500_500_1/gv6dzjvt9otnamcxn3379wo6imb7r8xh.jpg"),
Product("Reebok BB 4000 II", 129, "Классическая модель, переродившаяся в новом поколении. Вдохновлённые баскетбольной площадкой и прославившиеся на улицах кроссовки Reebok теперь доступны в высокой и низкой версиях.", "https://superstep.ru/upload/resize_cache/iblock/05b/2hzf550tkok2q71w7zngg7dc8fc61grh/800_800_2/RB100074941R00.jpg"),
Product("Nike Dunk Low Mini Swoosh", 229, "Модель Nike Dunk Low с верхом из кожи серого цвета с сетчатыми язычками и подкладкой. Черные накладки из гладкой кожи с текстурированным кожаным свушем на каждой стороне отсылают к образу панды.", "https://superstep.ru/upload/resize_cache/iblock/3d5/bdpt28vhwx43az2bz6o369i7xxi2ffx6/800_800_2/NKFN7808001.jpg"),
Product("Puma CA Pro Lux III", 100, "Кроссовки CA Pro вдохновлены оригинальной моделью California, которую выпустили в 80-х годах. Классический и одновременно современный вид, с перфорированным мыском", "https://superstep.ru/upload/resize_cache/iblock/195/2vmu66vpk9wjg9dneaf5606x6ke74nuh/800_800_2/PM39520302.jpg"),
Product("Reebok CLUB C REVENGE", 119, "Кроссовки Reebok CLUB C REVENGE в ретро-теннистой стилистике с контрастным логотипом, боковыми полосами и задником дополнят знаковый образ", "https://superstep.ru/upload/resize_cache/iblock/ccd/mujsc86xa5kbsxdur2da8n6jbjldtfr1/800_800_2/RB100075005R00.jpg"),
Product("Vans Old Skool", 88, "Классика от Vans, Old Skool - модель, на которых впервые появилась знаменитая белая полоса. Верх выполнен из плотного канваса и натуральной кожи.", "https://superstep.ru/upload/resize_cache/iblock/b84/3r68ex5d8kisg430jryzu4wfk6l3vszz/800_800_2/VN000D3HY281.jpg"),
Product("Asics JAPAN S", 119, "Кроссовки JAPAN S созданы на основе одной из моделей, выпущенных в стиле ретро. Винтажная баскетбольная эстетика JAPAN S также сочетается с культовыми деталями, характерными для кортов, которую носили баскетболисты 1980-х годов", "https://superstep.ru/upload/resize_cache/iblock/354/hrbs1scetskivsu0kroxn6uqghbg1qds/800_800_2/AS1191A328104.jpg"),
Product("AIR FORCE 1 '07", 229, "Самая узнаваемая модель Nike Air Force 1 '07 сейчас можно точно назвать классикой бренда. Изначально разработанная для баскетбола система амортизации Nike Air обеспечивает легкость и комфорт на весь день.", "https://superstep.ru/upload/resize_cache/iblock/93c/vom7t758af1z7s4q2i8xwdb9ron94n3x/800_800_2/NKDV7584001.jpg"),
Product("Adidas SAMBAE", 190, "Если перенестись в 1960-е годы, то можно обнаружить, что профессиональные футболисты носят adidas Samba, точно такие же, как эта модель. Ну, почти такие же. Обновленные для нового поколения, они имеют мягкий носок, который обеспечит комфорт", "https://superstep.ru/upload/resize_cache/iblock/306/u90v023w2wfakooj8q7fwi8mlpbt7b2o/800_800_2/ADID0440R00.jpg"),
Product("Nike DUNK LOW ESS SNKR", 229, "Кроссовки DUNK LOW ESS SNKR, отделанные роскошной гладкой атласной тканью, выглядят более нарядно, чем обычные кроссовки, но по-прежнему достаточно спортивные.", "https://superstep.ru/upload/resize_cache/iblock/5e1/gxofp14130e0rh0fqvbesq16yu2qspjq/800_800_2/NKDX5931001.jpg"),
Product("New Balance 550", 209, "Для всех ценителей винтажных моделей мы рады представить модель 550 от New Balance! В 1989 году эта модель была разработана специально для баскетбола, сегодня же она дополнит стильный повседневный образ.", "https://superstep.ru/upload/resize_cache/iblock/436/fvf2thy2wchh8zdej62k3998cokjon22/800_800_2/NBBBW550DP.jpg")
),
    private val context: Context,
    private val changeNumberItemsListener: ShoppingCartFragment
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)

    private val managementCart = ManagementCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            .load(item.imageUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.binding.image)

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