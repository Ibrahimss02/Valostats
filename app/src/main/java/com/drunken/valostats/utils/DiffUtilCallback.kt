package com.drunken.valostats.utils

import androidx.recyclerview.widget.DiffUtil
import com.drunken.valostats.models.Agent
import com.drunken.valostats.models.Bundle
import com.drunken.valostats.models.Map
import com.drunken.valostats.models.Spray

class AgentDiffUtil : DiffUtil.ItemCallback<Agent>(){
    override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
        return oldItem == newItem
    }
}

class MapDiffUtil : DiffUtil.ItemCallback<Map>(){
    override fun areItemsTheSame(oldItem: Map, newItem: Map): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Map, newItem: Map): Boolean {
        return oldItem == newItem
    }
}

class BundleDiffUtil : DiffUtil.ItemCallback<Bundle>(){
    override fun areItemsTheSame(oldItem: Bundle, newItem: Bundle): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Bundle, newItem: Bundle): Boolean {
        return oldItem == newItem
    }
}

class SprayDiffUtil : DiffUtil.ItemCallback<Spray>(){
    override fun areItemsTheSame(oldItem: Spray, newItem: Spray): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Spray, newItem: Spray): Boolean {
        return oldItem == newItem
    }
}