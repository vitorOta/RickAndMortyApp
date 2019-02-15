package com.vitorota.rickandmorty.data.character.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitorota.rickandmorty.data.DataEntity
import com.vitorota.rickandmorty.data.character.entities.FavoriteCharacterEntity.Companion.TABLE_NAME
import com.vitorota.rickandmorty.data.character.entity.Character

/**
 *
 * @author Vitor Ota
 * @since 14/02/2019
 */

@Entity(tableName = TABLE_NAME)
data class FavoriteCharacterEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
//    var origin: Episode?,
//    var location: Location?,
    //TODO add origin and location
    var image: String
//    var episode: List<String>?
) : DataEntity<Character> {
    override fun toDomain() =
        Character(
            id = id,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            origin = null,
            location = null,
            image = image,
            episode = null
        )

    companion object {
        const val TABLE_NAME = "CHARACTERS"
    }
}