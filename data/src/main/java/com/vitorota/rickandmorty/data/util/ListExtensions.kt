package com.vitorota.rickandmorty.data.util

import com.vitorota.rickandmorty.data.Schema


/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */

fun <T> List<Schema<T>>.toDomain(): List<T> {
    return this.map { it.toDomain() }
}