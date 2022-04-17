package com.drunken.valostats.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drunken.valostats.databinding.AgentItemLayoutBinding
import com.drunken.valostats.databinding.BundleItemLayoutBinding
import com.drunken.valostats.databinding.MapItemLayoutBinding
import com.drunken.valostats.databinding.SprayItemLayoutBinding
import com.drunken.valostats.models.Agent
import com.drunken.valostats.models.Bundle
import com.drunken.valostats.models.Map
import com.drunken.valostats.models.Spray

class HomepageAdapter<T : Any, B : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val layoutId: Int,
) : ListAdapter<T, ViewHolder<B>>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }
}

open class ViewHolder<B : ViewDataBinding>(private val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Any) {
        Log.i("Debug Geming", item.toString())
        when (item) {
            is Agent -> {
                (binding as AgentItemLayoutBinding).apply {
                    agent = item
                    executePendingBindings()
                }
            }
            is Map -> {
                (binding as MapItemLayoutBinding).apply {
                    map = item
                    executePendingBindings()
                }
            }
            is Bundle -> {
                (binding as BundleItemLayoutBinding).apply {
                    bundle = item
                    executePendingBindings()
                }
            }
            is Spray -> {
                (binding as SprayItemLayoutBinding).apply {
                    spray = item
                    executePendingBindings()
                }
            }
        }
    }
}