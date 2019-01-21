package com.vitorota.rickandmorty.domain

import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.data.episode.entity.Episode
import com.vitorota.rickandmorty.data.location.entity.Location
import java.util.*


/**
 *
 * @author Vitor Ota
 * @since 21/01/2019
 */

val mockCharacters = arrayListOf(
    Character(1, "Rick", "alive", "human", "", "male", null, null, "rick", null),
    Character(2, "Morty", "alive", "human", "", "male", null, null, "morty", null),
    Character(3, "Summer", "alive", "human", "", "female", null, null, "summer", null),
    Character(4, "Beth", "alive", "human", "", "female", null, null, "beth", null),
    Character(5, "Jerry", "alive", "human", "", "male", null, null, "jerry", null)
)


val mockEpisodes = arrayListOf(
    Episode(1, "Pilot", Calendar.Builder().setDate(2013, 12, 2).build().time, "S01E01", null),
    Episode(2, "Lawnmower Dog", Calendar.Builder().setDate(2013, 12, 9).build().time, "S01E02", null),
    Episode(3, "Anatomy Park", Calendar.Builder().setDate(2013, 12, 16).build().time, "S01E03", null),
    Episode(4, "M. Night Shaym-Aliens!", Calendar.Builder().setDate(2014, 1, 13).build().time, "S01E04", null),
    Episode(5, "Meeseeks and Destroy", Calendar.Builder().setDate(2014, 1, 20).build().time, "S01E05", null)
)


val mockLocations = arrayListOf(
    Location(1, "Earth (C-137)", "Planet", "Dimension C-137", null),
    Location(2, "Abadango", "Cluster", "unknown", null),
    Location(3, "Citadel of Ricks", "Space station", "unknown", null),
    Location(4, "Worldender's lair", "Planet", "unknown", null),
    Location(5, "Anatomy Park", "Microverse", "Dimension C-137", null)
)