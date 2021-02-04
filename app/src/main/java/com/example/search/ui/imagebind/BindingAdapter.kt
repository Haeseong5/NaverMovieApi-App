package com.example.search.ui.imagebind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.search.R


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bindPoster")
    fun bindBackPoster(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
//            .placeholder(R.drawable.loading)
            .error(R.drawable.ic_launcher_background)
            .into(view)
        
    }
}
