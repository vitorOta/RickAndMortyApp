package com.vitorota.rickandmorty.features.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.databinding.ItemCharacterBinding
import com.vitorota.rickandmorty.features.character.list.ListCharactersAdapter.CharacterViewHolder

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */
class ListCharactersAdapter(
    private val onItemClicked: (Character) -> Unit
) : ListAdapter<Character, CharacterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(ItemCharacterBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        with(holder.binding) {
            root.setOnClickListener { onItemClicked(character) }
            tvName.text = character.name
            sdvImage.setImageURI(character.image)
        }
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}

object DIFF_CALLBACK : ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}