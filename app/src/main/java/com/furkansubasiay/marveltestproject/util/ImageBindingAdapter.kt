package com.furkansubasiay.marveltestproject.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by FURKAN SUBAŞIAY on 2020-07-01.
 */


@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}
