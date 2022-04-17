package com.drunken.valostats.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.drunken.valostats.R
import com.drunken.valostats.databinding.AgentItemLayoutBinding
import com.drunken.valostats.models.Agent
import com.drunken.valostats.views.adapters.HomepageAdapter

@BindingAdapter("bindImageFromUrl")
fun ImageView.bindImage(url: String?) {
    Glide.with(this)
        .clear(this)
    val requestOption = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.img_placeholder).centerCrop()

    url?.apply {
        Glide.with(this@bindImage.context)
            .load(url)
            .apply(requestOption)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this@bindImage)
    }
}

@BindingAdapter("displayUrl", "animationUrl", "staticUrl")
fun ImageView.bindSprayImage(url: String, urlAnimation: String?, urlStatic: String?) {
    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    Glide.with(this)
        .clear(this)
    when {
        urlAnimation != null -> {
            Glide.with(this.context)
                .asGif()
                .load(urlAnimation)
                .transition(withCrossFade(factory))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.img_placeholder)
                .into(this)
        }
        urlStatic != null -> {
            Glide.with(this.context)
                .load(urlStatic)
                .transition(withCrossFade(factory))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.img_placeholder)
                .into(this)
        }
        else -> {
            Glide.with(this.context)
                .load(url)
                .transition(withCrossFade(factory))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.img_placeholder)
                .into(this)
        }
    }
}
