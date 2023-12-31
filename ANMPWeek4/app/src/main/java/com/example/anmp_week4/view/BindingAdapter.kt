package com.example.anmp_week4.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

//@BindingAdapter("android:imageUrl")
//fun loadPhotoURL(imageView: ImageView, url:String) {
//    val picasso = Picasso.Builder(imageView.context)
//    picasso.listener { picasso, uri, exception ->
//        exception.printStackTrace()
//    }
//    picasso.build().load(url).into(imageView)
//}


@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        val picasso = Picasso.Builder(imageView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(url).into(imageView)
    }
}
