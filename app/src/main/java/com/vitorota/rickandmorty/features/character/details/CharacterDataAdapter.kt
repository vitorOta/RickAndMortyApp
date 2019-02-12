package com.vitorota.rickandmorty.features.character.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.features.character.details.CharacterDataAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_character_data.view.*

/**
 *
 * @author Vitor Ota
 * @since 12/02/2019
 */
class CharacterDataAdapter : ListAdapter<Pair<String, String>, ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemView) {
            itemCharacterData_tvLabel.text = item.first
            itemCharacterData_tvValue.text = item.second
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

object DIFF_CALLBACK : DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean {
        return oldItem.second == newItem.second
    }
}