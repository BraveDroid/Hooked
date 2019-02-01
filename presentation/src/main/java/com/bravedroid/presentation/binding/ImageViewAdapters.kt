package com.bravedroid.presentation.binding


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bravedroid.presentation.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageUrl", "requestListener"])
fun ImageView.bindRequestListener(imageUrl: String?, requestListener: RequestListener<Drawable>?) {
    val options: RequestOptions = RequestOptions()
    .placeholder(R.drawable.cover_placeholder)
    .error(R.drawable.cover_error)

    Glide.with(context.applicationContext)
        .setDefaultRequestOptions(options)
        .load(imageUrl)
        .listener(requestListener)
        .into(this)

}

