package com.evendred.basicapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(url: String) {
    Glide.with(context).load(url).into(this)
}