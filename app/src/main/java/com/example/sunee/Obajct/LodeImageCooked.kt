package com.example.sunee.Obajct

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object LodeImageCooked {
    fun uplode(context: Context, url: String, imageView: ImageView) {
        context.let {
            Glide.with(it)
                .load(url)
                .apply(RequestOptions())
                .into(imageView)
        }
    }
}