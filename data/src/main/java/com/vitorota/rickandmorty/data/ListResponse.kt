package com.vitorota.rickandmorty.data

/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */
data class ListResponse<T>(
    var info: InfoObject,
    var results: List<T>
)

data class InfoObject(
    var count: Int,
    var pages: Int,
    var next: String,
    var prev: String
)