package com.vitorota.rickandmorty.features.character.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitorota.rickandmorty.databinding.ItemCharacterDataBinding
import com.vitorota.rickandmorty.features.character.details.CharacterDataAdapter.ViewHolder

/**
 *
 * @author Vitor Ota
 * @since 12/02/2019
 */
class CharacterDataAdapter : ListAdapter<Pair<String, String>, ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCharacterDataBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            itemCharacterDataTvLabel.text = item.first
            itemCharacterDataTvValue.text = item.second
        }
    }

    class ViewHolder(val binding: ItemCharacterDataBinding) : RecyclerView.ViewHolder(binding.root)
}

object DIFF_CALLBACK : DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem.second == newItem.second
    }
}