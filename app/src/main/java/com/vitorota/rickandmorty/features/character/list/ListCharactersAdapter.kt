package com.vitorota.rickandmorty.features.character.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import kotlinx.android.synthetic.main.item_character.view.*

/**
 *
 * @author Vitor Ota
 * @since 24/01/2019
 */
class ListCharactersAdapter(
    private val onItemClicked: (Character) -> Unit
) : ListAdapter<Character, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        val holder = CharacterViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        with(holder.itemView) {
            setOnClickListener { onItemClicked(character) }
            tvName.text = character.name
            sdvImage.setImageURI(character.image)
        }
    }


}

object DIFF_CALLBACK : ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}