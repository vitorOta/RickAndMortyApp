package com.vitorota.rickandmorty.data.character.entity

import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.location.entity.Location

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */


/*
id	int	The id of the character.
name	string	The name of the character.
status	string	The status of the character ('Alive', 'Dead' or 'unknown').
species	string	The species of the character.
type	string	The type or subspecies of the character.
gender	string	The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
origin	object	Name and link to the character's origin location.
location	object	Name and link to the character's last known location endpoint.
image	string (url)	Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
episode	array (urls)	List of episodes in which this character appeared.
url	string (url)	Link to the character's own URL endpoint.
*/

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Episode?,
    var location: Location?,
    var image: String,
    var episode: List<String>?
)