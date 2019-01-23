package com.vitorota.rickandmorty.data.episode.entity

import java.util.*

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */

/*
id	int	The id of the episode.
name	string	The name of the episode.
air_date	string	The air date of the episode.
episode	string	The code of the episode.
characters	array (urls)	List of characters who have been seen in the episode.
url	string (url)	Link to the episode's own endpoint.
created	string	Time at which the episode was created in the database.
*/
class Episode(
    var id:Int,
    var name:String,
    var airDate:String?, //should be date, but the api response wasn't okay
    var episode:String?,
    var characters:List<String>?
)