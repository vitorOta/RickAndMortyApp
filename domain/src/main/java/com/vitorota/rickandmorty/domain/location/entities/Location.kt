package com.vitorota.rickandmorty.data.location.entity

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */

/*
id	int	The id of the location.
name	string	The name of the location.
type	string	The type of the location.
dimension	string	The dimension in which the location is located.
residents	array (urls)	List of character who have been last seen in the location.
url	string (url)	Link to the location's own endpoint.
created	string	Time at which the location was created in the database.
*/

data class Location(
    var id: Int,
    var name: String,
    var type: String?,
    var dimension: String?,
    var residents: List<String>?
)