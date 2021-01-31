package com.example.search.ui.imagebind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bindPoster")
    fun bindBackPoster(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
//            .placeholder(R.drawable.loading)
//            .error(R.drawable.ic_baseline_error_outline_24)
            .into(view)
        
    }
}
