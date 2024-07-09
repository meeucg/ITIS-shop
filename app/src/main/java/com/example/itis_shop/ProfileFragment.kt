package com.example.itis_shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itis_shop.databinding.FragmentProfileBinding
import com.example.itis_shop.storage.Product
import com.example.itis_shop.storage.user_id
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding?.run {
            tvTitle1.setOnClickListener{
                storage.addToCatalog(product = Product(
                    name = "Nike Monarch",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vel mauris quis eros aliquet scelerisque. Donec efficitur, felis non sagittis interdum, enim est rhoncus purus, sed scelerisque massa massa sit amet quam. Sed dolor quam, suscipit non nibh a, fermentum imperdiet quam. In feugiat, dolor non consequat volutpat, neque ligula dapibus quam, in posuere tellus enim vitae tortor. In semper auctor maximus. Curabitur molestie efficitur imperdiet. Nunc ut feugiat urna. Curabitur a purus ut purus hendrerit porta. Aliquam tristique gravida ante, eu faucibus libero malesuada id. Cras cursus ligula ex, sed mollis libero vulputate sit amet.\n" +
                            "\n" +
                            "Suspendisse vel neque at dui lacinia hendrerit eget at velit. Pellentesque tellus ipsum, tincidunt sed justo non, accumsan ornare nisi. Donec iaculis tincidunt elit, a accumsan sapien rhoncus ac. Cras ornare suscipit odio, ac egestas leo sagittis sit amet. Nulla sit amet quam in ligula malesuada condimentum sed quis est. Praesent suscipit fermentum quam vel commodo. Maecenas sit amet ipsum vel felis maximus sollicitudin vitae non felis. Ut vel sem sit amet libero tristique porttitor fringilla in dui. Aliquam sit amet convallis nibh. Morbi sapien turpis, suscipit non felis eu, ornare volutpat ipsum. Cras non nisi vitae velit ornare euismod vitae vitae dui. Nullam.\n" +
                            "\n",
                    price=400,
                    imageUrl = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/a487f641-a6d6-4acc-9b49-79c70ddd6ee8/air-monarch-iv-amp-mens-workout-shoes-dChTJ7.png"),
                    onFail = {
                        Snackbar.make(
                        root, "Such product already exists", Snackbar.LENGTH_LONG).show()
                    })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}