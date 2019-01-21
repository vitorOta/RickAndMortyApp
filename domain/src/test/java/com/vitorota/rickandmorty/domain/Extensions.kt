package com.vitorota.rickandmorty.domain


/**
 *
 * @author Vitor Ota
 * @since 21/01/2019
 */

//don't change this (if you need to do, review all tests that use this val)
const val pageSize = 3

fun <T> List<T>.getItemsPage(page:Int):List<T>{
    val fromIndex = page * pageSize - pageSize
    var toIndex = fromIndex + pageSize

    if (toIndex > this.size) {
        toIndex = this.size
    }

    return this.subList(fromIndex, toIndex).toList()
}